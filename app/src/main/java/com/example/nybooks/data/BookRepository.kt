package com.example.nybooks.data

import com.example.nybooks.data.model.Results
import com.example.nybooks.data.response.RetrofitInstance
import com.example.nybooks.utils.Constants.Companion.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

open class BookRepository {

    open suspend fun getBooks() : Response<Results> {
        return withContext(Dispatchers.Default){
            RetrofitInstance.API_SERVICE.getBooks("hardcover-fiction", API_KEY)
        }
    }
}