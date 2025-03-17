package com.example.bookstore.data.data_source.books

import androidx.room.Delete
import com.example.bookstore.data.data_source.cart.models.BookRoom
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface BooksService {

    @GET("books/v1/volumes?printType=books")
    suspend fun getNextBookPage(
        @Query("q") title: String,
        @Query("startIndex") startIndex: Int? = null,
        @Query("maxResults") maxResults: Int? = null
    ): Response

    @GET
    suspend fun getBookByLink(
        @Url link: String?
    ): Item
}

data class Item(
    val selfLink: String?,
    val volumeInfo: ItemInfo
)

data class ItemInfo(
    val title: String,
    val imageLinks: Link?,
    val authors: List<String>?,
    val categories: List<String>?
)

data class Link(
    val smallThumbnail: String?
)

data class Response(
    val kind: String?,
    val items: List<Item>?
)