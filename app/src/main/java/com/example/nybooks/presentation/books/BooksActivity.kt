package com.example.nybooks.presentation.books

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.R
import com.example.nybooks.data.MainRepository
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        toolbarMain.title = getString(R.string.actionBar_title)
        setSupportActionBar(toolbarMain)

        viewModel = ViewModelProvider(this, MainViewModel.MainViewModelFactory(MainRepository())).get(MainViewModel::class.java)
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