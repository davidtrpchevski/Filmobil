package com.david.filmobil.di

import android.net.ConnectivityManager
import com.david.filmobil.BuildConfig
import com.david.filmobil.network.connectivity.ApiKeyInterceptor
import com.david.filmobil.network.connectivity.ConnectivityInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object InterceptorsModule {

    @Provides
    @IntoSet
    fun provideHttpLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

    @Provides
    @IntoSet
    fun provideApiConstantsInterceptor(): Interceptor = ApiKeyInterceptor()

    @Provides
    @IntoSet
    fun provideConnectivityInterceptor(connectivityManager: ConnectivityManager): Interceptor =
        ConnectivityInterceptor(connectivityManager)
}