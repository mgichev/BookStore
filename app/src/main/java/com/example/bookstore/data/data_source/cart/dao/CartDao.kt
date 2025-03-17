package com.example.bookstore.data.data_source.cart.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.bookstore.data.data_source.cart.models.BookRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM BookRoom")
    fun getAll(): Flow<List<BookRoom>>

    @Insert
    suspend fun insert(cart: BookRoom)


    @Query ("SELECT COUNT(*) FROM BookRoom")
    suspend fun getNumberCart() : Int

    @Delete
    suspend fun remove(bookRoom: BookRoom)
}