package com.task.data.di

import android.content.Context
import com.task.data.datasource.pref.Pref

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.task.data.BuildConfig
import com.task.data.BuildConfig.DEBUG
import com.task.data.R

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun providePref(
        @ApplicationContext appContext: Context
    ): Pref =
        Pref(
            context = appContext,
            name = "${BuildConfig.LIBRARY_PACKAGE_NAME}${BuildConfig.BUILD_TYPE}",
            isDebug = DEBUG,
        )
}
