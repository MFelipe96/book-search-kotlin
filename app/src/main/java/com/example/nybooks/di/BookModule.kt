package com.example.nybooks.di

import com.example.nybooks.data.BookRepository
import com.example.nybooks.presentation.books.BookViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookModule = module {
    factory {
        BookRepository()
    }

    viewModel {
        BookViewModel(
            repository = get()
        )
    }
}