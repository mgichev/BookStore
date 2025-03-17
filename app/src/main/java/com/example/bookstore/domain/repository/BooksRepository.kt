package com.example.bookstore.domain.repository

import com.example.bookstore.domain.model.Book

interface BooksRepository {
    suspend fun getBooks() : List<Book>

    suspend fun getBooksByText(text: String) : List<Book>

    suspend fun getBookByLink(link: String) : Book
}