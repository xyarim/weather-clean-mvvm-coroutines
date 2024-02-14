package com.xyarim.data.source.remote

import com.xyarim.data.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

internal interface RemoteApiService {
    @GET(FORECAST_ROUTE)
    suspend fun getForecast(@Query(DAYS_QUERY_KEY) days: Int): ForecastResponse

    companion object {
        private const val BASE_URL = BuildConfig.BASE_URL
        private const val DAYS_QUERY_KEY = "days"
        private const val FORECAST_ROUTE = "forecast.json"

        fun create(okHttpClient: OkHttpClient): RemoteApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RemoteApiService::class.java)
        }
    }
}