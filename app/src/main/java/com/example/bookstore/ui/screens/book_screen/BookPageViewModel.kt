package com.example.bookstore.ui.screens.book_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstore.domain.model.Book
import com.example.bookstore.domain.use_cases.GetBookByLinkUseCase
import com.example.bookstore.domain.use_cases.GetBookListByTextUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookPageViewModel(private val getBookByLinkUseCase: GetBookByLinkUseCase) : ViewModel() {

    private var _book = MutableLiveData<Book>()
    val book: LiveData<Book> = _book

    fun fetchData(link: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val value = getBookByLinkUseCase.execute(link)
            _book.postValue(value)
        }
    }
}