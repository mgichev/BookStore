package com.example.bookstore.ui.screens.search_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstore.domain.model.Book
import com.example.bookstore.domain.use_cases.GetBookListByTextUseCase
import com.example.bookstore.domain.use_cases.InsertBookCartListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchBooksViewModel(
    private val getBookListByTextUseCase: GetBookListByTextUseCase,
    private val insertBookCartListUseCase: InsertBookCartListUseCase
) : ViewModel() {

    private var _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    private var job: Job? = null

    fun fetchData(text: String) {
        job?.cancel()
        if (text.isNotEmpty()) {
            job = viewModelScope.launch(Dispatchers.IO) {
                val bookList = getBookListByTextUseCase.execute(text)
                _books.postValue(bookList)
            }
        }
    }

    fun insertBookToCart(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            insertBookCartListUseCase.execute(book)
        }
    }
}