package com.example.bookstore.ui.screens.library_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bookstore.databinding.ItemLibraryBookBinding
import com.example.bookstore.domain.model.Book

class LibraryAdapter(private val bookList: List<Book>) :
    RecyclerView.Adapter<LibraryAdapter.LibraryVH>() {

    inner class LibraryVH(private val binding: ItemLibraryBookBinding) : ViewHolder(binding.root) {
        fun bind(pos: Int) {
            binding.recentTV.text = bookList[pos].title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryVH {
        val binding =
            ItemLibraryBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibraryVH(binding)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: LibraryVH, position: Int) {
        holder.bind(position)
    }
}