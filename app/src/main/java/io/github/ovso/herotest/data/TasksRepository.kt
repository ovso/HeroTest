package io.github.ovso.herotest.data

import io.github.ovso.herotest.data.local.TasksLocalDataSource
import io.github.ovso.herotest.data.remote.UserRemoteDataSource
import io.reactivex.Single

class TasksRepository(
  private val userDataSource: UserRemoteDataSource,
  private val localDataSource: TasksLocalDataSource
) : TasksDataSource {

  override fun users(): Single<Any> {
    return Single.fromCallable { "" }
  }
}
