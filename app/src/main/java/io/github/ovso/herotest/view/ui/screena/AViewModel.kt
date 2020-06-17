package io.github.ovso.herotest.view.ui.screena

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.savedstate.SavedStateRegistryOwner
import io.github.ovso.herotest.data.TasksRepository
import io.github.ovso.herotest.data.toAModels
import io.github.ovso.herotest.data.view.AModel
import io.github.ovso.herotest.utils.RxBus
import io.github.ovso.herotest.utils.SchedulerProvider
import io.github.ovso.herotest.view.base.DisposableViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign
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
    compositeDisposable += RxBus.toObservable().subscribe {
      if (it is RxBusOnTextChanged) {
        onTextChanged(it.text)
      }
    }
  }

  private fun onTextChanged(text: String) {
    if (text.isBlank().not()) {
      disposeUsers()
      reqSearch(text)
    }
  }

  private fun disposeUsers() {
    reqDisposable?.dispose()
    reqDisposable = null
  }

  private fun reqSearch(text: String) {
    fun onSuccess(items: List<AModel>) {
      _items.value = items
    }
    reqDisposable = repository.users(text)
      .delay(DELAY_TIME, TimeUnit.MILLISECONDS)
      .map { it.toAModels() }
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, Timber::e)
  }

  data class RxBusOnTextChanged(val text: String)
  data class RxBusFavItem(val id: Int)

  companion object {
    const val DELAY_TIME = 300L
  }
}
