package com.xyarim.data.di

import com.xyarim.data.source.LocalDataSource
import com.xyarim.data.source.local.LocalDataSourceImpl
import com.xyarim.data.source.RemoteDataSource
import com.xyarim.data.source.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {
    @Binds
    @Singleton
    fun provideRemoteDataSource(repositoryImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @Singleton
    fun provideLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource
}