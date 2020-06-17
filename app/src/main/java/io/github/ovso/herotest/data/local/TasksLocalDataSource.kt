package io.github.ovso.herotest.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import io.github.ovso.herotest.App
import io.github.ovso.herotest.data.local.model.FavEntity
import io.reactivex.Single

class TasksLocalDataSource(private val context: Context) {
  private val database = (context.applicationContext as App).database

  fun favList(): LiveData<List<FavEntity>> {
    return database.favDao().favorites()
  }

}
