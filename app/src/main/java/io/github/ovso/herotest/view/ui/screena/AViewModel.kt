package io.github.ovso.herotest.view.ui.screena

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.savedstate.SavedStateRegistryOwner
import io.github.ovso.herotest.data.TasksRepository
import io.github.ovso.herotest.data.toUserModels
import io.github.ovso.herotest.data.view.UserModel
import io.github.ovso.herotest.utils.RxBus
import io.github.ovso.herotest.utils.SchedulerProvider
import io.github.ovso.herotest.view.base.DisposableViewModel
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import java.util.concurrent.TimeUnit

class AViewModel(
  private val owner: SavedStateRegistryOwner,
  private val repository: TasksRepository
) : DisposableViewModel() {

  private val _items = MutableLiveData<List<UserModel>>()
  val items: LiveData<List<UserModel>> = _items

  init {
    compositeDisposable += RxBus.toObservable().subscribe {
      if (it is RxBusOnTextChanged) {
        onTextChanged(it.text)
      }
    }
  }

  private fun onTextChanged(text: String) {
    clearDisposable()
    reqSearch(text)
  }

  private fun reqSearch(text: String) {
    fun onSuccess(items: List<UserModel>) {
      _items.value = items
    }
    compositeDisposable += repository.users(text)
      .delay(DELAY_TIME, TimeUnit.MILLISECONDS)
      .map { it.toUserModels() }
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, Timber::e)
  }

  data class RxBusOnTextChanged(val text: String)

  companion object {
    const val DELAY_TIME = 300L
  }
}
