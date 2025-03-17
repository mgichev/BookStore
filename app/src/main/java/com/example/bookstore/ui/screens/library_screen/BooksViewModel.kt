package com.example.bookstore.ui.screens.library_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstore.domain.model.Book
import com.example.bookstore.domain.use_cases.GetBookListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BooksViewModel(private val getBookListUseCase: GetBookListUseCase) : ViewModel() {

    private var _books =  MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books


    init {

    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}