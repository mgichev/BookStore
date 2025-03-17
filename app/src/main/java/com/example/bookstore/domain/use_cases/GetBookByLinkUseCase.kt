package com.example.bookstore.domain.use_cases

import com.example.bookstore.domain.model.Book
import com.example.bookstore.domain.repository.BooksRepository

class GetBookByLinkUseCase(private val booksRepository: BooksRepository) {
    suspend fun execute(link: String) : Book {
        return booksRepository.getBookByLink(link)
    }
}