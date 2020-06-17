package io.github.ovso.herotest.view.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class DisposableViewModel : ViewModel() {
  protected val compositeDisposable = CompositeDisposable()

  override fun onCleared() {
    super.onCleared()
    clearDisposable()
  }

  protected fun clearDisposable() {
    compositeDisposable.clear()
  }

}
