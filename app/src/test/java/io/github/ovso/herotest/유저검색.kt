@file:Suppress("NonAsciiCharacters", "ClassName")

package io.github.ovso.herotest

import io.github.ovso.herotest.data.remote.UserRemoteDataSource
import io.github.ovso.herotest.data.toUserModels
import io.github.ovso.herotest.data.view.UserModel
import io.reactivex.schedulers.Schedulers
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class 유저검색 {
  private val userRemoteDataSource by lazy { UserRemoteDataSource }

  @Test
  fun `검색`() {
    fun onSuccess(items: List<UserModel>) {
      println(items.toString())
    }

    fun onFailure(t: Throwable) {
      t.printStackTrace()
    }

    val user = "jaeho"
    userRemoteDataSource
      .users(user)
      .map { it.items.toUserModels() }
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
  }
}

object SchedulerProvider {
  fun io() = Schedulers.trampoline()
  fun ui() = Schedulers.trampoline()
}
