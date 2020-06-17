package io.github.ovso.herotest.data

import androidx.lifecycle.LiveData
import io.github.ovso.herotest.data.local.TasksLocalDataSource
import io.github.ovso.herotest.data.local.model.FavEntity
import io.github.ovso.herotest.data.remote.UserRemoteDataSource
import io.github.ovso.herotest.data.remote.model.UsersResponse
import io.reactivex.Single

class TasksRepository(
  private val remoteDataSource: UserRemoteDataSource,
  private val localDataSource: TasksLocalDataSource
) : TasksDataSource {

  override fun users(q:String): Single<UsersResponse> {
    return remoteDataSource.users(q)
  }

  override fun favList(): LiveData<List<FavEntity>> {
    return localDataSource.favList()
  }
}
