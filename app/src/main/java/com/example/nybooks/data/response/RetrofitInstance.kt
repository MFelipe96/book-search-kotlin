package com.example.nybooks.data.response

import com.example.nybooks.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client : OkHttpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()


    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}