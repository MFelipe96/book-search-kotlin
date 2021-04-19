package com.example.nybooks.presentation.books

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.model.BookDetails
import com.example.nybooks.databinding.BookItemBinding

class BooksAdapter(
        private val books: List<BookDetails>
) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BookItemBinding.inflate(inflater)
        return BooksViewHolder(binding)
    }

    override fun getItemCount() = books.count()

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(books[position].bookDetails[0])
    }

    class BooksViewHolder(private val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root){

        //A vinculação de dados gera propriedades de uma forma mais limpa
        fun bind(book : Book){
            binding.book = book
            binding.executePendingBindings()
        }
    }
}