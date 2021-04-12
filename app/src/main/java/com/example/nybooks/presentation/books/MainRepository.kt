package com.example.nybooks.presentation.books

import com.example.nybooks.data.model.Results
import com.example.nybooks.data.response.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainRepository {

    suspend fun getBooks() : Response<Results> {
        return withContext(Dispatchers.Default){
            RetrofitInstance.api.getBooks("hardcover-fiction", "2xD0VG0ZKiAILttyZ2zapxM29FXO4fAi")
        }
    }
}