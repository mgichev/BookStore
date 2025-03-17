package com.example.bookstore.di

import androidx.room.Room
import com.example.bookstore.data.data_source.cart.dao.CartDao
import com.example.bookstore.data.data_source.cart.db.BookDatabase
import org.koin.dsl.module

val dbModule = module {
    single<CartDao> {

        val db = Room.databaseBuilder(
            context = get(),
            BookDatabase::class.java,
            "book.db"
        ).build()

        db.cartDao()
    }
}