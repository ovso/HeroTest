package io.github.ovso.herotest.utils

import io.reactivex.subjects.PublishSubject

object RxBus {
  private val subject = PublishSubject.create<Any>()
  fun send(obj: Any) {
    subject.onNext(obj)
  }

  fun toObservable(): PublishSubject<Any> {
    return subject
  }
}