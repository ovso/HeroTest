package io.github.ovso.herotest.data

import io.reactivex.Single

interface TasksDataSource {
  fun users(): Single<Any>
}
