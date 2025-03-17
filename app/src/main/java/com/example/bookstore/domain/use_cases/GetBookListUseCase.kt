package com.example.bookstore.domain.use_cases

import com.example.bookstore.domain.model.Book
import com.example.bookstore.domain.repository.BooksRepository

class GetBookListUseCase(private val booksRepository: BooksRepository) {
    suspend fun execute(): List<Book> = booksRepository.getBooks()
}