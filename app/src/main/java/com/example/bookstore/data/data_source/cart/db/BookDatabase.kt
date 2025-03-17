package com.example.bookstore.data.data_source.cart.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookstore.data.data_source.cart.dao.CartDao
import com.example.bookstore.data.data_source.cart.models.BookRoom

@Database(entities = [BookRoom::class], version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}