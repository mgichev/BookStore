package com.example.bookstore.ui.screens.shop_screen.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.bookstore.R
import com.example.bookstore.databinding.ItemBookBinding
import com.example.bookstore.databinding.ItemMoreBtnBinding
import com.example.bookstore.domain.model.Book

class ShopBooksAdapter(
    private val booksList: List<Book>,
    private val onCardClicked: (link: String) -> Unit,
    private val onMoreBtnClicked: () -> Unit,
    private val onBuyBtnClicked: (book: Book) -> Unit,
    private val onToCartBtnClicked: (book: Book) -> Unit
) :
    RecyclerView.Adapter<ViewHolder>() {

    private lateinit var context: Context

    companion object {
        const val BOOK_VH = 0
        const val MORE_BTN_VH = 1
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    inner class BookViewHolder(private val binding: ItemBookBinding) : ViewHolder(binding.root) {
        fun bind(id: Int) {
            val item = booksList[id]
            binding.book = item
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                onCardClicked(item.link ?: "")
            }

            binding.buyBtn.setOnClickListener {
                onBuyBtnClicked(item)
            }

            binding.toCartBtn.setOnClickListener {
                onToCartBtnClicked(item)
            }
        }
    }

    inner class MoreButtonViewHolder(private val binding: ItemMoreBtnBinding) :
        ViewHolder(binding.root) {
        fun bind() {
            binding.moreBtn.setOnClickListener {
                onMoreBtnClicked()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == booksList.size)
            MORE_BTN_VH
        else
            BOOK_VH
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType == BOOK_VH) {
            val binding =
                ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return BookViewHolder(binding)
        } else {
            val binding =
                ItemMoreBtnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MoreButtonViewHolder(binding)
        }
    }

    override fun getItemCount() = booksList.size + 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.itemViewType == BOOK_VH) {
            val _holder = holder as BookViewHolder
            _holder.bind(position)
        } else {
            val _holder = holder as MoreButtonViewHolder
            _holder.bind()
        }
    }

}