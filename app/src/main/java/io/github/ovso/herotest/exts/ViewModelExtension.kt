package io.github.ovso.herotest.exts

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import io.github.ovso.herotest.App

fun Fragment.getViewModelFactory(): ViewModelFactory {
  val repository = (requireContext().applicationContext as App).taskRepository
  return ViewModelFactory(
    repository = repository,
    owner = this,
    defaultArgs = arguments
  )
}

fun ComponentActivity.getViewModelFactory(): ViewModelFactory {
  val repository = (applicationContext as App).taskRepository
  return ViewModelFactory(
    repository = repository,
    owner = this,
    intent = intent
  )
}
