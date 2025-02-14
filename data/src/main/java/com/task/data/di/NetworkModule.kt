package com.task.data.di


import com.task.data.network.EndPoint
import com.task.data.network.interceptor.HeaderInterceptor
import com.task.data.network.interceptor.StatusCodeInterceptor
import com.task.data.network.service.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.task.data.BuildConfig


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //OkHttpClient
    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor,
        statusCodeInterceptor: StatusCodeInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder().apply {

        connectTimeout(EndPoint.TIME_OUT, TimeUnit.SECONDS)
        readTimeout(EndPoint.TIME_OUT, TimeUnit.SECONDS)
        writeTimeout(EndPoint.TIME_OUT, TimeUnit.SECONDS)

        addInterceptor(headerInterceptor)
        addInterceptor(statusCodeInterceptor)
        addInterceptor(loggingInterceptor)


    }.build()

    //Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory
    ): Retrofit = Retrofit.Builder().client(okHttpClient).baseUrl(BuildConfig.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(coroutineCallAdapterFactory).build()

    //ApiService
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

}
