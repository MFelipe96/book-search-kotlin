package com.example.nybooks.presentation.books

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nybooks.data.MainRepository
import com.example.nybooks.data.model.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainViewModel(private val repository: MainRepository) : ViewModel() {

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

    class MainViewModelFactory(
        private val repository: MainRepository
    ) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }

    }

}