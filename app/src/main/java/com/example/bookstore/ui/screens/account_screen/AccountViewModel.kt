package com.example.bookstore.ui.screens.account_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookstore.domain.use_cases.GetNumberCartBookUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel(private val getNumberCartBookUseCase: GetNumberCartBookUseCase) :
    ViewModel() {

    private var _numberCart = MutableLiveData<Int>()
    val numberCart: LiveData<Int> = _numberCart

    private var _numberRead = MutableLiveData<Int>()
    val numberRead: LiveData<Int> = _numberRead

    private var _numberBought = MutableLiveData<Int>()
    val numberBought: LiveData<Int> = _numberBought

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            val number = getNumberCartBookUseCase.execute()
            _numberCart.postValue(number)
        }
    }

}