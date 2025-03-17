package com.example.bookstore.domain.use_cases

import com.example.bookstore.domain.model.Book
import com.example.bookstore.domain.repository.CartRepository

class InsertBookCartListUseCase(private val cartRepository: CartRepository) {
    suspend fun execute(book: Book) {
        cartRepository.insertBook(book)
    }
}