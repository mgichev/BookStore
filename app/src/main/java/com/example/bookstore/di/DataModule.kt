package com.example.bookstore.di

import com.example.bookstore.data.data_source.books.BooksDataSource
import com.example.bookstore.data.data_source.books.RetrofitBooksDataSource
import com.example.bookstore.data.data_source.cart.RoomCartDataSource
import com.example.bookstore.data.repository.BooksRepositoryImpl
import com.example.bookstore.data.repository.CartRepositoryImpl
import com.example.bookstore.domain.repository.BooksRepository
import com.example.bookstore.domain.repository.CartRepository
import org.koin.dsl.module

val dataModule = module {
    single<BooksDataSource> {
        RetrofitBooksDataSource(get())
    }

    single<BooksRepository> {
        BooksRepositoryImpl(
            booksDataSource = get()
        )
    }

    single<RoomCartDataSource> {
        RoomCartDataSource(
            cartDao = get()
        )
    }

    single<CartRepository> {
        CartRepositoryImpl(
            roomCartDataSource = get(),
            booksService = get()
        )
    }
}