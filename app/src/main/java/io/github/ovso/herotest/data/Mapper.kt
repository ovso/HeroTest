package io.github.ovso.herotest.data

import androidx.annotation.WorkerThread
import com.google.gson.Gson
import io.github.ovso.herotest.data.local.model.FavEntity
import io.github.ovso.herotest.data.remote.model.UserResponse
import io.github.ovso.herotest.data.remote.model.UsersResponse
import io.github.ovso.herotest.data.view.AModel
import io.github.ovso.herotest.data.view.BModel
import io.reactivex.rxkotlin.toObservable

@WorkerThread
fun List<UserResponse>.toAModels(): List<AModel> {
  val g = Gson()
  return this.toObservable().map {
    it.toAModel(g)
  }.toList().blockingGet()
}

@WorkerThread
fun UsersResponse.toAModels(): List<AModel> {
  val g = Gson()
  return this.items.toObservable().map {
    it.toAModel(g)
  }.toList().blockingGet()
}

@WorkerThread
fun UserResponse.toAModel(g: Gson): AModel {
  return g.fromJson(g.toJson(this), AModel::class.java)
}

@WorkerThread
fun AModel.toFavEntity(g: Gson): FavEntity {
  return g.fromJson(g.toJson(this), FavEntity::class.java)
}

@WorkerThread
fun BModel.toFavEntity(g: Gson): FavEntity {
  return g.fromJson(g.toJson(this), FavEntity::class.java)
}

@WorkerThread
fun FavEntity.toBModel(g: Gson): BModel {
  return g.fromJson(g.toJson(this), BModel::class.java)
}

@WorkerThread
fun List<FavEntity>.toBModels(): List<BModel> {
  val g = Gson()
  return this.toObservable().map {
    it.toBModel(g)
  }.toList().blockingGet()

}
