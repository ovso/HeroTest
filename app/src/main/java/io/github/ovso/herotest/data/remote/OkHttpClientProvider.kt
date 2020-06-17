package io.github.ovso.herotest.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpClientProvider {
  val okHttpClient: OkHttpClient

  init {
    okHttpClient = createClient()
  }

  private fun createClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.readTimeout(
      TIMEOUT_SECONDS.toLong(),
      TimeUnit.SECONDS
    )
    httpClient.connectTimeout(
      TIMEOUT_SECONDS.toLong(),
      TimeUnit.SECONDS
    )
    httpClient.addInterceptor(
      Interceptor { chain: Interceptor.Chain ->
        val builder =
          chain.request().newBuilder().header("Content-Type", "application/json")
        chain.proceed(builder.build())
      }
    )
    httpClient.addInterceptor(
      HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    )
    return httpClient.build()
  }

  private const val TIMEOUT_SECONDS = 60
}
