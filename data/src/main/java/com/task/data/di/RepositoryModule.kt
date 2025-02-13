package com.task.data.di


import com.task.data.network.service.ApiService
import com.task.data.repository.HistoryRepository
import com.task.data.repository.HistoryRepositoryImpl
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
    fun provideRepository(apiService: ApiService): HistoryRepository {
        return HistoryRepositoryImpl(apiService = apiService)
    }

}
