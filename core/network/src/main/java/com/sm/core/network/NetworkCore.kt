package com.sm.core.network

import android.util.Log
import com.sm.core.network.interceptors.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface NetworkCore {
    fun <T> getCoreNetwork(baseUrl: String, endPoint: Class<T>): T
}

internal class NetworkCoreImpl : NetworkCore {

    init {
        Log.d(this.javaClass.name, "Initialising Network Core...")
    }

    override fun <T> getCoreNetwork(
        baseUrl: String,
        endPoint: Class<T>
    ): T =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(endPoint)

    private fun getOkHttpClient(
        connectTimeout: Long = 20,
        writeTimeout: Long = 20,
        readTimeout: Long = 30
    ) =
        OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor())
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
            .build()
}
