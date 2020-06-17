package io.github.ovso.herotest

import io.github.ovso.herotest.data.remote.UserRemoteDataSource
import io.reactivex.schedulers.Schedulers
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GithubTest {
  private val userRemoteDataSource by lazy { UserRemoteDataSource }

  @Test
  fun `유저검색`() {
    fun onSuccess(any: Any) {
      println(any.toString())
    }

    fun onFailure(t: Throwable) {
      t.printStackTrace()
    }

    val user = "jaeho"
    userRemoteDataSource
      .users(user)
      .subscribeOn(SchedulerProvider.io())
      .observeOn(SchedulerProvider.ui())
      .subscribe(::onSuccess, ::onFailure)
  }
}

object SchedulerProvider {
  fun io() = Schedulers.trampoline()
  fun ui() = Schedulers.trampoline()
}
