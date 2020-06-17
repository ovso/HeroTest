package io.github.ovso.herotest.view.ui.screena

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.ovso.herotest.R
import io.github.ovso.herotest.view.base.OnTextChangedListener
import timber.log.Timber

class AFragment : Fragment(), OnTextChangedListener {

  companion object {
    fun newInstance() = AFragment()
  }

  private lateinit var viewModel: AViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_a, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(AViewModel::class.java)
    // TODO: Use the ViewModel
  }

  override fun onTextChanged(text: String) {
    Timber.i("A onTextChanged($text)")
  }
}
