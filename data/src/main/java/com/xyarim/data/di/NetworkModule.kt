package com.xyarim.data.di

import com.xyarim.data.source.remote.AuthInterceptor
import com.xyarim.data.source.remote.RemoteApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @IntoSet
    @Provides
    fun provideAuthInterceptor(): Interceptor = AuthInterceptor()

    @IntoSet
    @Provides
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    @Provides
    fun provideOkHttpClient(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient =
        OkHttpClient.Builder().apply {
            interceptors.forEach { addInterceptor(it) }
        }.build()

    @Singleton
    @Provides
    fun provideRemoteService(client: OkHttpClient): RemoteApiService =
        RemoteApiService.create(client)

}