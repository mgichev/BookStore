package com.example.bookstore.data.data_source.cart.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookRoom(
    val link: String?,
    @PrimaryKey(autoGenerate = true) val id: Long? = null
)
