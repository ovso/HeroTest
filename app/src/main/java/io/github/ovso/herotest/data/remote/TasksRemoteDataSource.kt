package io.github.ovso.herotest.data.remote

import io.reactivex.Single

class TasksRemoteDataSource {

    fun users(channelId: String): Single<Any> {
        return Single.fromCallable { "" }
    }
}
