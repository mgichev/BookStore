package com.example.bookstore.domain.use_cases

import com.example.bookstore.domain.model.Book
import com.example.bookstore.domain.repository.BooksRepository

class GetBookListByTextUseCase(private val booksRepository: BooksRepository) {
    suspend fun execute(bookTitle: String) : List<Book> = booksRepository.getBooksByText(bookTitle)
}