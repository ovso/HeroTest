package io.github.ovso.herotest.view.ui.screenb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.savedstate.SavedStateRegistryOwner
import io.github.ovso.herotest.data.TasksRepository
import io.github.ovso.herotest.data.toBModels
import io.github.ovso.herotest.data.view.BModel
import io.github.ovso.herotest.utils.RxBus
import io.github.ovso.herotest.utils.SchedulerProvider
import io.github.ovso.herotest.view.base.DisposableViewModel
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BViewModel(
  private val owner: SavedStateRegistryOwner,
  private val repository: TasksRepository
) : DisposableViewModel() {

  private val _items = MutableLiveData<List<BModel>>()
  val items: LiveData<List<BModel>> = _items
  private var favDisposable: Disposable? = null

  init {
    compositeDisposable += RxBus.toObservable().subscribe {
      if (it is RxBusOnTextChanged) {
        onTextChanged(it.text)
      }
    }
    observeFav()
  }

  private fun observeFav() {
    repository.favList().observe(owner, Observer {
      compositeDisposable +=
        Single.fromCallable(it::toBModels)
          .subscribeOn(SchedulerProvider.io())
          .observeOn(SchedulerProvider.ui())
          .subscribe({ models ->
            _items.value = models
          }, Timber::e)
    })
  }

  private fun onTextChanged(text: String) {
    Timber.d("B onTextChanged($text)")

    when (text.isBlank().not()) {
      true -> search(text)
      else -> showAllList()
    }
  }

  private fun showAllList() {
    fun onSuccess(items: List<BModel>) {
      _items.value = items
    }

    disposeFav()
    favDisposable = repository.favListRx()
      .delay(150, TimeUnit.MILLISECONDS)
      .map { it.toBModels() }
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, Timber::e)

  }

  private fun search(text: String) {
    fun onSuccess(items: List<BModel>) {
      _items.value = items
    }

    disposeFav()
    favDisposable = repository.favListRx()
      .delay(150, TimeUnit.MILLISECONDS)
      .map { it.toBModels() }
      .flatMapObservable {
        Observable.fromIterable(it)
      }.filter { it.login.indexOf(text) != -1 }
      .toList()
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, Timber::e)
  }

  private fun disposeFav() {
    favDisposable?.dispose()
    favDisposable = null
  }

  override fun onCleared() {
    super.onCleared()
    disposeFav()
  }

  data class RxBusOnTextChanged(val text: String)
}
