package io.github.ovso.herotest.view.ui.main

object Screen {
  var now: Now = Now.A
  fun selected(pagerPosition: Int) {
    now = if (pagerPosition == 0) Now.A else Now.B
  }

  enum class Now {
    A, B
  }
}

