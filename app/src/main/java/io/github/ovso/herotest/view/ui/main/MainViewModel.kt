package io.github.ovso.herotest.view.ui.main

import io.github.ovso.herotest.view.base.DisposableViewModel
import timber.log.Timber

class MainViewModel : DisposableViewModel() {

    fun onSearchTextChanged(text: String) {
        Timber.i("onSearchTextChanged($text)")
    }
}