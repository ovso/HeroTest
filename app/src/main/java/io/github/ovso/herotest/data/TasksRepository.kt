package io.github.ovso.herotest.data

import io.github.ovso.herotest.data.TasksDataSource
import io.github.ovso.herotest.data.local.TasksLocalDataSource
import io.github.ovso.herotest.data.remote.TasksRemoteDataSource
import io.reactivex.Single

class TasksRepository(
  private val remoteDataSource: TasksRemoteDataSource,
  private val localDataSource: TasksLocalDataSource
) : TasksDataSource {

  override fun users(): Single<Any> {
    return Single.fromCallable { "" }
  }
}
