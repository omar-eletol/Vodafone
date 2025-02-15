package com.task.data.di


import com.task.data.datasource.database.WeatherDao
import com.task.data.datasource.network.service.ApiService
import com.task.data.repository.Repository
import com.task.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService, weatherDao: WeatherDao): Repository {
        return RepositoryImpl(apiService = apiService, weatherDao = weatherDao)
    }

}
