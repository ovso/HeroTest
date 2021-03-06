package io.github.ovso.herotest.view.ui.screena

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.herotest.R
import io.github.ovso.herotest.databinding.FragmentABinding
import io.github.ovso.herotest.exts.getViewModelFactory
import io.github.ovso.herotest.view.base.DataBindingFragment
import io.github.ovso.herotest.view.ui.screena.adapter.AAdapter
import kotlinx.android.synthetic.main.fragment_a.*

class AFragment : DataBindingFragment<FragmentABinding>(R.layout.fragment_a) {

  private val adapter by lazy { AAdapter() }

  companion object {
    fun newInstance() = AFragment()
  }

  private val viewModel: AViewModel by viewModels { getViewModelFactory() }

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
    rv_a.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
    rv_a.adapter = adapter
  }
}
