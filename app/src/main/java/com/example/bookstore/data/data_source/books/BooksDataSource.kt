package com.example.bookstore.data.data_source.books

import com.example.bookstore.domain.model.Book

interface BooksDataSource {

    companion object {
        const val DEFAULT_BOOKS_PAGE = 10
    }

    suspend fun getBooks(title: String? = null, page: Int? = null) : List<Book>

    suspend fun getBooksByText(text: String) : List<Book>

    suspend fun getBookByLink(link: String) : Book

}