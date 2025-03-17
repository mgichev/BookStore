package com.example.bookstore.ui.screens.cart_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstore.domain.model.Book
import com.example.bookstore.domain.use_cases.GetCartListUseCase
import com.example.bookstore.domain.use_cases.RemoveBookInCartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartListViewModel(
    private val getCartListUseCase: GetCartListUseCase,
    private val removeBookInCartUseCase: RemoveBookInCartUseCase
) : ViewModel() {

    init {
        fetchData()
    }

    private var _cartList = MutableLiveData<List<Book>>()
    val cartList: LiveData<List<Book>> = _cartList

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getCartListUseCase.execute()
            list.collect { items ->
                _cartList.postValue(items)
            }
        }
    }

    fun removeItem(index: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val list: MutableList<Book> = _cartList.value?.toMutableList() ?: mutableListOf()
            if (index < list.size)
                list.removeAt(index)

            val book = _cartList.value?.get(index)

            _cartList.postValue(list)
            if (book != null) {

                removeBookInCartUseCase.execute(book)
            }
        }
    }
}