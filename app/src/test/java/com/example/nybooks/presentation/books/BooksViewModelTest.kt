package com.example.nybooks.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nybooks.data.MainRepository
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.model.BookDetails
import com.example.nybooks.data.model.Results
import com.github.testcoroutinesrule.TestCoroutineRule
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() = MockitoAnnotations.initMocks(this)

    @Mock
    private lateinit var booksLiveDataObserver: Observer<Results?>

    private lateinit var viewModel: MainViewModel

    @Test
    fun `when view model getBooks get success then sets booksList`(){

        testCoroutineRule.runBlockingTest {
            launch(Dispatchers.Main){
                //Arrange
                val books = listOf(
                        Book("Título 1", "Atuhor 1", "Editora 1")
                )
                val booksDetails = listOf(BookDetails(books))
                val mockRepo = MockRepository(Response.success(Results(booksDetails)))
                viewModel = MainViewModel(mockRepo)
                viewModel.booksList.observeForever(booksLiveDataObserver)

                //Act
                viewModel.getBooksFromRepository()

                //Assert
                verify(booksLiveDataObserver).onChanged(Results(booksDetails))
            }
        }

    }
}

class MockRepository(private val result: Response<Results>) : MainRepository() {
    override suspend fun getBooks() : Response<Results> = result
}