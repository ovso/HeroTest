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

  fun favListRx(): Single<List<FavEntity>> {
    return database.favDao().favoritesRx()
  }

  fun favEntity(id: Int): LiveData<FavEntity?> {
    return database.favDao().getEntity(id)
  }

  fun favEntityRx(id: Int): Single<FavEntity?> {
    return database.favDao().getEntityRx(id)
  }

}
