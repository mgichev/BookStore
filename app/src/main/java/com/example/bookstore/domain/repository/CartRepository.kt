package com.example.bookstore.domain.repository

import com.example.bookstore.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun getAllBooks(): Flow<List<Book>>

    suspend fun insertBook(book: Book)

    suspend fun getNumberCartBook() : Int

    suspend fun remove(book: Book)

}