package com.david.filmobil.network.connectivity

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(private val connectivityManager: ConnectivityManager) : Interceptor {

    private val networkCapabilities
        get() = connectivityManager.getNetworkCapabilities(
            connectivityManager.activeNetwork
        )

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) throw NoConnectivityException()

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isConnected(): Boolean {
        return networkCapabilities != null && (networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true ||
                networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true ||
                networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true ||
                networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) == true ||
                networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_VPN) == true)
    }
}
