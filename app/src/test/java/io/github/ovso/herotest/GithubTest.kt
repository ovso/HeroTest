package io.github.ovso.herotest

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GithubTest {
  @Test
  fun `유저검색`() {

  }
}

object SchedulerProvider {
  fun io() = Schedulers.trampoline()

  fun ui() = Schedulers.trampoline()
}
