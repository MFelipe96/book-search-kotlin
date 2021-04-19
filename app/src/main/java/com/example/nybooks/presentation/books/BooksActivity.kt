package com.example.nybooks.presentation.books

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.R
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.loading.*

class BooksActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        //val binding : ViewDataBinding? = DataBindingUtil.setContentView(this, R.layout.activity_books)

        toolbarMain.title = getString(R.string.actionBar_title)
        setSupportActionBar(toolbarMain)

        viewModel = ViewModelProvider(this, MainViewModel.MainViewModelFactory(MainRepository())).get(MainViewModel::class.java)
        viewModel.getBooksFromRepository()
        viewModel.booksList.observe(this, Observer {response ->
            with(recyclerBooks){
                layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                setHasFixedSize(true)
                if(response.isSuccessful) {
                    adapter = response.body()?.results?.let { BooksAdapter(it) }
                }else{
                    //todo: criar tratamento de erro
                    Log.d("FAILED", "onCreate: ${response.errorBody().toString()} /// ${response.code()}" )
                }
            }
        })
    }
}