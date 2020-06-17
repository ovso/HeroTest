package io.github.ovso.herotest.view.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.ovso.herotest.view.base.DisposableViewModel
import timber.log.Timber

class MainViewModel : DisposableViewModel() {

  private val _text = MutableLiveData<String>()
  val text:LiveData<String> = _text

  fun onSearchTextChanged(text: String) {
    Timber.i("onSearchTextChanged($text)")
    _text.value = text
  }
}
