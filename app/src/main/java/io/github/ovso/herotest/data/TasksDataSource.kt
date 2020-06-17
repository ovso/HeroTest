package io.github.ovso.herotest.data

import androidx.lifecycle.LiveData
import io.github.ovso.herotest.data.local.model.FavEntity
import io.github.ovso.herotest.data.remote.model.UsersResponse
import io.reactivex.Single

interface TasksDataSource {
  fun users(q: String): Single<UsersResponse>
  fun favList(): LiveData<List<FavEntity>>
  fun favEntity(id:Int): LiveData<FavEntity?>
  fun favEntityRx(id:Int): Single<FavEntity?>
}
