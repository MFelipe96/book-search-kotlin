package com.example.nybooks.presentation.books

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nybooks.data.BookRepository
import com.example.nybooks.data.model.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookViewModel(private val repository: BookRepository) : ViewModel() {

    val booksList = MutableLiveData<Results?>()

    fun getBooksFromRepository() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.Default) {
                repository.getBooks()
            }
            if (result.isSuccessful)
                booksList.value = result.body()
            else {
                //todo: implement error handle
                Log.d("FAILED", "onCreate: ${result.message()} /// ${result.code()}")
            }
        }
    }
}