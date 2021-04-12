package com.example.nybooks.data.model

import com.google.gson.annotations.SerializedName

data class Book(
    val title: String,
    val author: String,
    val publisher: String
)

data class Results  (
    val results : List<BookDetails>
)

data class BookDetails(
    @SerializedName("book_details")
    val bookDetails: List<Book>
)