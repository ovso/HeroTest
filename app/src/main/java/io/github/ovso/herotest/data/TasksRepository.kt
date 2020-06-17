package io.github.ovso.herotest.data

import io.github.ovso.herotest.data.local.TasksLocalDataSource
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
}
