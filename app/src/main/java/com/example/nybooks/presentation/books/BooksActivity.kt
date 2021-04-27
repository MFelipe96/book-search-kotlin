package com.example.nybooks.presentation.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.R
import com.example.nybooks.data.BookRepository
import com.example.nybooks.di.bookModule
import kotlinx.android.synthetic.main.activity_books.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.logger.AndroidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

class BooksActivity : AppCompatActivity() {

    private val viewModel : BookViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        toolbarMain.title = getString(R.string.actionBar_title)
        setSupportActionBar(toolbarMain)

        viewModel.getBooksFromRepository()
        viewModel.booksList.observe(this, Observer {books ->
            with(recyclerBooks){
                layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                setHasFixedSize(true)
                    adapter = books?.results?.let { BooksAdapter(it) }
            }
        })
    }
}