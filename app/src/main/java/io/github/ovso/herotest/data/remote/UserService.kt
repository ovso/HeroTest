package io.github.ovso.herotest.data.remote

import com.google.gson.JsonElement
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
  @GET("search/users")
  fun users(@Query("q") q: String): Single<JsonElement>
}