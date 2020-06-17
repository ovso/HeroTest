package io.github.ovso.herotest.view.ui.screenb

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.ovso.herotest.R

class BFragment : Fragment() {

  companion object {
    fun newInstance() = BFragment()
  }

  private lateinit var viewModel: BViewModel

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_b, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(BViewModel::class.java)
    // TODO: Use the ViewModel
  }

}
