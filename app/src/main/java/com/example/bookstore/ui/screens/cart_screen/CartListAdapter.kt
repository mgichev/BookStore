package com.example.bookstore.ui.screens.cart_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bookstore.databinding.ItemCartBookBinding
import com.example.bookstore.domain.model.Book

class CartListAdapter(private val list: List<Book>) :
    RecyclerView.Adapter<CartListAdapter.CartListViewHolder>() {

    inner class CartListViewHolder(private val binding: ItemCartBookBinding) : ViewHolder(binding.root) {
        fun bind(id: Int) {
            val item = list[id]
            binding.book = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartListViewHolder {
        val binding = ItemCartBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartListViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        holder.bind(position)
    }
}