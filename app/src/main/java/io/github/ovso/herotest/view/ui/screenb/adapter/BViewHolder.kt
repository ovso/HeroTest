package io.github.ovso.herotest.view.ui.screenb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import io.github.ovso.herotest.App
import io.github.ovso.herotest.R
import io.github.ovso.herotest.data.toFavEntity
import io.github.ovso.herotest.data.view.BModel
import io.github.ovso.herotest.databinding.ItemAllBinding
import timber.log.Timber

class BViewHolder private constructor(private val binding: ItemAllBinding) :
  RecyclerView.ViewHolder(binding.root) {
  private val context = itemView.context
  private lateinit var item: BModel
  fun onBindViewHolder(item: BModel) {
    this.item = item
    Glide.with(itemView).load(item.avatar_url).into(binding.ivItemAll)
    binding.tvItemAllDescription.text = item.toString()
    binding.ivItemAllFav.setOnClickListener(this::onFavClick)
  }

  private fun onFavClick(it: View) {
    it.isSelected = !it.isSelected
    when (it.isSelected) {
      true -> addFav()
      else -> delFav()
    }
  }

  private fun delFav() {
    Thread {
      val delete = ((context.applicationContext) as? App)
        ?.database?.favDao()
        ?.delete(item.toFavEntity(Gson()))
      Timber.i("delete = $delete")
    }.start()
  }

  private fun addFav() {
    Thread {
      ((context.applicationContext) as? App)
        ?.database?.favDao()
        ?.insert(item.toFavEntity(Gson()))
    }.start()
  }

  companion object {
    fun create(parent: ViewGroup): BViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(
        R.layout.item_all,
        parent,
        false
      )
      return BViewHolder(ItemAllBinding.bind(view))
    }
  }

}
