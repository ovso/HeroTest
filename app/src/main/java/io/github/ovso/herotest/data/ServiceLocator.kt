package io.github.ovso.herotest.data

import android.content.Context
import io.github.ovso.herotest.data.local.TasksLocalDataSource
import io.github.ovso.herotest.data.remote.UserRemoteDataSource

object ServiceLocator {

  @Volatile
  var tasksRepository: TasksRepository? = null

  fun provideTasksRepository(context: Context): TasksRepository {
    synchronized(this) {
      return tasksRepository ?: createTasksRepository(context)
    }
  }

  private fun createTasksRepository(context: Context): TasksRepository {
    val newRepo = TasksRepository(UserRemoteDataSource, TasksLocalDataSource(context))
    tasksRepository = newRepo
    return newRepo
  }
}
