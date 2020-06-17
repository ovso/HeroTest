package io.github.ovso.herotest.view.ui.screena

import androidx.fragment.app.viewModels
import io.github.ovso.herotest.R
import io.github.ovso.herotest.databinding.FragmentABinding
import io.github.ovso.herotest.exts.getViewModelFactory
import io.github.ovso.herotest.view.base.DataBindingFragment
import io.github.ovso.herotest.view.base.OnTextChangedListener
import timber.log.Timber

class AFragment : DataBindingFragment<FragmentABinding>(R.layout.fragment_a),
  OnTextChangedListener {

  companion object {
    fun newInstance() = AFragment()
  }

  private val viewModel: AViewModel by viewModels { getViewModelFactory() }

  override fun onTextChanged(text: String) {
    Timber.i("A onTextChanged($text)")
  }
}
