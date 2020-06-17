package io.github.ovso.herotest.data

import androidx.annotation.WorkerThread
import com.google.gson.Gson
import io.github.ovso.herotest.data.remote.model.UserResponse
import io.github.ovso.herotest.data.remote.model.UsersResponse
import io.github.ovso.herotest.data.view.UserModel
import io.reactivex.rxkotlin.toObservable

@WorkerThread
fun List<UserResponse>.toUserModels(): List<UserModel> {
  val g = Gson()
  return this.toObservable().map {
    it.toUserModel(g)
  }.toList().blockingGet()
}

@WorkerThread
fun UsersResponse.toUserModels(): List<UserModel> {
  val g = Gson()
  return this.items.toObservable().map {
    it.toUserModel(g)
  }.toList().blockingGet()
}

@WorkerThread
fun UserResponse.toUserModel(g: Gson): UserModel {
  return g.fromJson(g.toJson(this), UserModel::class.java)
}
