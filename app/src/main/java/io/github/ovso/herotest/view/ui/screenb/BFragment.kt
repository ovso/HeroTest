package io.github.ovso.herotest.view.ui.screenb

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.herotest.R
import io.github.ovso.herotest.databinding.FragmentBBinding
import io.github.ovso.herotest.exts.getViewModelFactory
import io.github.ovso.herotest.view.base.DataBindingFragment
import io.github.ovso.herotest.view.ui.screenb.adapter.BAdapter
import kotlinx.android.synthetic.main.fragment_b.*

class BFragment : DataBindingFragment<FragmentBBinding>(R.layout.fragment_b) {

  private val adapter by lazy { BAdapter() }

  companion object {
    fun newInstance() = BFragment()
  }

  private val viewModel: BViewModel by viewModels { getViewModelFactory() }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.viewModel = viewModel
    setupRv()
    observe()
  }

  private fun observe() {
    val owner = viewLifecycleOwner
    viewModel.items.observe(owner, Observer {
      adapter.submitList(it)
    })
  }

  private fun setupRv() {
    rv_b.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
    rv_b.adapter = adapter
  }
}
