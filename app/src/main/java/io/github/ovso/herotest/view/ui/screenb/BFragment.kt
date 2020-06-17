package io.github.ovso.herotest.view.ui.screenb

import androidx.fragment.app.viewModels
import io.github.ovso.herotest.R
import io.github.ovso.herotest.databinding.FragmentBBinding
import io.github.ovso.herotest.exts.getViewModelFactory
import io.github.ovso.herotest.view.base.DataBindingFragment
import io.github.ovso.herotest.view.base.OnTextChangedListener
import timber.log.Timber

class BFragment : DataBindingFragment<FragmentBBinding>(R.layout.fragment_b),
  OnTextChangedListener {

  companion object {
    fun newInstance() = BFragment()
  }

  private val viewModel: BViewModel by viewModels { getViewModelFactory() }


  override fun onTextChanged(text: String) {
    Timber.i("B onTextChanged($text)")
  }

}
