package com.example.nybooks.data.response

import com.example.nybooks.data.model.Book
import com.example.nybooks.data.model.Results
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Headers("Accept: application/json", "Content-type:application/json")
    @GET("lists.json")
    suspend fun getBooks(@Query("list") list: String, @Query("api-key") api_key: String) : Response<Results>
}