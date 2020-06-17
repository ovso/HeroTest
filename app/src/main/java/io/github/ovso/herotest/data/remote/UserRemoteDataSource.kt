package io.github.ovso.herotest.data.remote

import com.google.gson.JsonElement
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object UserRemoteDataSource {
  private const val BASE_URL = "https://api.github.com/"

  private fun api(): UserService {
    return createRetrofit().create(UserService::class.java)
  }

  fun users(q: String): Single<JsonElement> {
    return api().users(q)
  }

  private fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .client(OkHttpClientProvider.okHttpClient)
      .build()
  }

}