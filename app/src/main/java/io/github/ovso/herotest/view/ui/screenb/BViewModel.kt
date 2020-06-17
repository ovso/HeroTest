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
import io.reactivex.Single
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber

class BViewModel(
  private val owner: SavedStateRegistryOwner,
  private val repository: TasksRepository
) : DisposableViewModel() {

  private val _items = MutableLiveData<List<BModel>>()
  val items: LiveData<List<BModel>> = _items

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

//    disposeUsers()
//    reqSearch(text)
  }

  data class RxBusOnTextChanged(val text: String)
}
