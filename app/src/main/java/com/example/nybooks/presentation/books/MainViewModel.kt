package com.example.nybooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.model.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val booksList = MutableLiveData<Response<Results>>()


    fun getBooksFromRepository(){
        CoroutineScope(Dispatchers.Main).launch {
            val books = withContext(Dispatchers.Default){
                repository.getBooks()
            }
            booksList.value = books
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