package io.github.ovso.herotest.data

import io.github.ovso.herotest.data.remote.model.UsersResponse
import io.reactivex.Single

interface TasksDataSource {
  fun users(q:String): Single<UsersResponse>
}
