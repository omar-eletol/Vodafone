package com.task.data.di

import android.content.Context
import com.task.data.datasource.network.interceptor.HeaderInterceptor
import com.task.data.datasource.network.interceptor.StatusCodeInterceptor
import com.task.data.datasource.pref.Pref

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InterceptorModule {


    //LoggingInterceptor
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level =
                HttpLoggingInterceptor.Level.BODY
        }

    //HeaderInterceptor
    @Provides
    @Singleton
    fun provideHeaderInterceptor(pref: Pref): HeaderInterceptor = HeaderInterceptor(pref = pref)

    //StatusCodeInterceptor
    @Provides
    @Singleton
    fun provideStatusCodeInterceptor(@ApplicationContext context: Context, pref: Pref) =
        StatusCodeInterceptor(context = context, pref = pref)


}
