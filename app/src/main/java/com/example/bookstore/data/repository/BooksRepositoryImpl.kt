package com.example.bookstore.data.repository

import com.example.bookstore.data.data_source.books.BooksDataSource
import com.example.bookstore.domain.model.Book
import com.example.bookstore.domain.repository.BooksRepository

class BooksRepositoryImpl(private val booksDataSource: BooksDataSource) : BooksRepository {
    override suspend fun getBooks(): List<Book> = booksDataSource.getBooks()

    override suspend fun getBooksByText(text: String): List<Book> {
        return booksDataSource.getBooksByText(text)
    }

    override suspend fun getBookByLink(link: String): Book {
        return booksDataSource.getBookByLink(link)
    }
}