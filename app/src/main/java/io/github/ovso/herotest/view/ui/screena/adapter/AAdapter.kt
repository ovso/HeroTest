package io.github.ovso.herotest.view.ui.screena.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.ovso.herotest.data.view.AModel

class AAdapter : ListAdapter<AModel, AViewHolder>(
  object : DiffUtil.ItemCallback<AModel>() {
    override fun areItemsTheSame(oldItem: AModel, newItem: AModel): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AModel, newItem: AModel): Boolean {
      return areItemsTheSame(oldItem, newItem)
    }
  }) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AViewHolder {
    return AViewHolder.create(parent)
  }

  override fun onBindViewHolder(holder: AViewHolder, position: Int) {
    holder.onBindViewHolder(getItem(position))
  }
}