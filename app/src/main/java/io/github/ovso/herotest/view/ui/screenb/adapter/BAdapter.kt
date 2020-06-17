package io.github.ovso.herotest.view.ui.screenb.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.herotest.data.view.BModel

class BAdapter : ListAdapter<BModel, BViewHolder>(
  object : DiffUtil.ItemCallback<BModel>() {
    override fun areItemsTheSame(oldItem: BModel, newItem: BModel): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BModel, newItem: BModel): Boolean {
      return areItemsTheSame(oldItem, newItem)
    }
  }) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BViewHolder {
    return BViewHolder.create(parent)
  }

  override fun onBindViewHolder(holder: BViewHolder, position: Int) {
    holder.onBindViewHolder(getItem(position))
  }
}
