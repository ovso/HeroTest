package io.github.ovso.herotest.view.ui.screena

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.savedstate.SavedStateRegistryOwner
import com.google.gson.Gson
import io.github.ovso.herotest.data.TasksRepository
import io.github.ovso.herotest.data.toAModels
import io.github.ovso.herotest.data.view.AModel
import io.github.ovso.herotest.utils.RxBus
import io.github.ovso.herotest.utils.SchedulerProvider
import io.github.ovso.herotest.view.base.DisposableViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import okhttp3.internal.toImmutableList
import timber.log.Timber
import java.util.concurrent.TimeUnit

class AViewModel(
  private val owner: SavedStateRegistryOwner,
  private val repository: TasksRepository
) : DisposableViewModel() {

  private var reqDisposable: Disposable? = null
  private val _items = MutableLiveData<List<AModel>>()
  val items: LiveData<List<AModel>> = _items

  init {
    compositeDisposable += RxBus.toObservable()
      .observeOn(SchedulerProvider.ui())
      .subscribe {
        if (it is RxBusOnTextChanged) {
          onTextChanged(it.text)
        }
      }

    observe()
  }

  private fun observe() {
    val g = Gson()
    repository.favList().observe(owner, Observer { entities ->
      nowItem?.let { models ->
        compositeDisposable += Observable.fromIterable(models).map {
          val toList = entities.filter { entity ->
            entity.id == it.id
          }.toList()
          it.isSelected = toList.count() > 0
          Timber.d("${it.login}, ${it.isSelected}")
          g.fromJson(g.toJson(it), AModel::class.java)
        }.toList().subscribe({
          _items.postValue(it)
        }, Timber::e)
      }
    })
  }

  private fun onTextChanged(text: String) {
    disposeUsers()
    if (text.isBlank().not()) {
      reqSearch(text)
    }
  }

  private fun disposeUsers() {
    reqDisposable?.dispose()
    reqDisposable = null
  }

  private var nowItem: List<AModel>? = null
  private fun reqSearch(text: String) {
    fun onSuccess(items: List<AModel>) {
      nowItem = items.toList().toImmutableList()
      _items.value = items
    }
    reqDisposable = repository.users(text)
      .delay(DELAY_TIME, TimeUnit.MILLISECONDS)
      .map { it.toAModels() }
      .flatMapObservable {
        Observable.fromIterable(it)
      }.map { model -> // DB에 있다면 isSelected = true
        val toList = repository.favListRx().blockingGet().filter {
          it.id == model.id
        }.toList()
        model.isSelected = toList.count() > 0
        model
      }.toList()
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, Timber::e)
  }

  data class RxBusOnTextChanged(val text: String)
  companion object {
    const val DELAY_TIME = 300L
  }
}
