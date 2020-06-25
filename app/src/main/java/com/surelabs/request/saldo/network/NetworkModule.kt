package com.surelabs.request.saldo.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkModule {

    const val BASE_URL = "DEFINED BASE URL PROJECT HWRE"
    const val FIREBASE_URL = "https://fcm.googleapis.com/"

    private fun getOkHttp(): OkHttpClient {

        //logging -> to record, logcat, biar kita tau catatan error lebih spesifik
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp())
            .build()
    }

    private fun getRetrofitFCM(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FIREBASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp())
            .build()
    }

    fun getService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }

    fun getFcmService(): ApiService {
        return getRetrofitFCM().create(ApiService::class.java)
    }
}