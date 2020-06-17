package io.github.ovso.herotest.view.ui.screenb

import io.github.ovso.herotest.utils.RxBus
import io.github.ovso.herotest.view.base.DisposableViewModel
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber

class BViewModel : DisposableViewModel() {

  init {
    compositeDisposable += RxBus.toObservable().subscribe {
      if(it is RxBusOnTextChanged) {
        Timber.d("$it")
      }
    }
  }

  data class RxBusOnTextChanged(val text: String)
}
