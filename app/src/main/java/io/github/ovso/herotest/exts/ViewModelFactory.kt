package io.github.ovso.herotest.exts

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import io.github.ovso.herotest.data.TasksRepository
import io.github.ovso.herotest.view.ui.main.MainViewModel
import io.github.ovso.herotest.view.ui.screena.AViewModel
import io.github.ovso.herotest.view.ui.screenb.BViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
  private val repository: TasksRepository,
  private val owner: SavedStateRegistryOwner,
  private val defaultArgs: Bundle? = null,
  private val intent: Intent? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

  override fun <T : ViewModel> create(
    key: String,
    modelClass: Class<T>,
    handle: SavedStateHandle
  ) = with(modelClass) {
    when {
      isAssignableFrom(MainViewModel::class.java) -> MainViewModel()
      isAssignableFrom(AViewModel::class.java) -> AViewModel()
      isAssignableFrom(BViewModel::class.java) -> BViewModel()
      else ->
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
  } as T
}
