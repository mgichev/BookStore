package com.example.bookstore.ui.screens.search_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bookstore.databinding.ItemBookBinding
import com.example.bookstore.domain.model.Book

class SearchBooksAdapter(
    private val list: List<Book>,
    private val onToCartClicked: (book: Book) -> Unit,
    private val onCardClicked: (link: String) -> Unit,
    private val onBuyClicked: () -> Unit,
) : RecyclerView.Adapter<SearchBooksAdapter.SearchVH>() {

    inner class SearchVH(private val binding: ItemBookBinding) : ViewHolder(binding.root) {
        fun bind(ind: Int) {
            val book = list[ind]
            binding.book = book
            binding.executePendingBindings()

            binding.toCartBtn.setOnClickListener {
                onToCartClicked(book)
            }

            binding.root.setOnClickListener {
                onCardClicked(book.link ?: "")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVH {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchVH(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchVH, position: Int) {
        holder.bind(position)
    }

}