package io.github.ovso.herotest.data.local

import android.content.Context
import io.github.ovso.herotest.App
import io.reactivex.Single

class TasksLocalDataSource(private val context: Context) {
  private val database = (context.applicationContext as App).database

  fun users(): Single<Any> {
    return Single.fromCallable { "" }
  }

}
