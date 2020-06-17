package io.github.ovso.herotest.view.ui.screena.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.ovso.herotest.R
import io.github.ovso.herotest.data.view.UserModel
import io.github.ovso.herotest.databinding.ItemAllBinding

class AViewHolder private constructor(private val binding: ItemAllBinding) :
  RecyclerView.ViewHolder(binding.root) {
  fun onBindViewHolder(item: UserModel) {
    Glide.with(itemView).load(item.avatar_url).into(binding.ivItemAll)
    binding.tvItemAllDescription.text = item.toString()
    binding.ivItemAllFav.setOnClickListener {
      it.isSelected = !it.isSelected
    }
  }

  companion object {
    fun create(parent: ViewGroup): AViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(
        R.layout.item_all,
        parent,
        false
      )
      val bind = ItemAllBinding.bind(view)
      return AViewHolder(bind)
    }
  }

}