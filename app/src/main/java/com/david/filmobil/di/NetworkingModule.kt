package com.david.filmobil.di

import android.content.Context
import android.net.ConnectivityManager
import com.david.filmobil.BuildConfig
import com.david.filmobil.network.RemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {

    @Provides
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    fun provideOkHttp(
        interceptorSet: Set<@JvmSuppressWildcards Interceptor>,
    ): OkHttpClient = with(OkHttpClient.Builder()) {
        interceptorSet.forEach {
            addInterceptor(it)
        }
        build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("${BuildConfig.API_URL}/${BuildConfig.API_VERSION}/")
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()

    @Provides
    fun provideRemoteService(retrofit: Retrofit): RemoteService = retrofit.create()

    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}