package com.example.bookstore.ui.screens.shop_screen.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstore.data.data_source.books.BooksDataSource
import com.example.bookstore.domain.model.Book
import com.example.bookstore.domain.use_cases.GetBookListUseCase
import com.example.bookstore.domain.use_cases.InsertBookCartListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopBooksViewModel(
    private val getBookListUseCase: GetBookListUseCase,
    private val insertBookCartListUseCase: InsertBookCartListUseCase
) : ViewModel() {

    private var _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    private var _toggleIsEmptyResult: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val toggleIsEmptyResult: LiveData<Boolean> = _toggleIsEmptyResult

    init {
        fetchData()
        Log.d("Init", "Init")
    }

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getBookListUseCase.execute()
            val bookList: List<Book> = _books.value.orEmpty() + result
            _books.postValue(bookList)
            if (result.size < BooksDataSource.DEFAULT_BOOKS_PAGE) {
                _toggleIsEmptyResult.postValue(!(toggleIsEmptyResult.value ?: false))
            }
        }
    }

    fun addToCart(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            insertBookCartListUseCase.execute(book)
        }
    }

    fun removeItem(index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val list: MutableList<Book> = _books.value?.toMutableList() ?: mutableListOf()
            if (index < list.size)
                list.removeAt(index)
            _books.postValue(list)
        }
    }
}