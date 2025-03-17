package com.example.bookstore.domain.use_cases

import com.example.bookstore.domain.repository.CartRepository

class GetCartListUseCase(private val cartRepository: CartRepository) {
    fun execute() = cartRepository.getAllBooks()
}