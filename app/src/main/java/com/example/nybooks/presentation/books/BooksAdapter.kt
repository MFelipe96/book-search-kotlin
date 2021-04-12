   package com.example.nybooks.presentation.books

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.R
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.model.BookDetails
import com.example.nybooks.data.model.Results
import kotlinx.android.synthetic.main.item_book.view.*

class BooksAdapter(
        private val books: List<BookDetails>
) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BooksViewHolder(view)
    }

    override fun getItemCount() = books.count()

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bindView(books[position].bookDetails[0])
        Log.d("bookSize", "onBindViewHolder: ${books.size} // ${books[0].bookDetails[0]}")
    }

    class BooksViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val title = view.titleText
        private val author = view.authorText
        private val publisher = view.publisherText

        fun bindView(book: Book){
            title.text = book.title
            author.text = "Autor(a): ${book.author}"
            publisher.text = "Editora: ${book.publisher}"
        }
    }
}