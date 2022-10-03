package com.david.filmobil.di

import android.content.Context
import com.crazylegend.toaster.Toaster
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToasterModule {
    @Provides
    @Singleton
    fun provideToaster(@ApplicationContext context: Context): Toaster = Toaster(context)
}