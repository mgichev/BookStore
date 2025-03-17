package com.example.bookstore.data.data_source.cart

import com.example.bookstore.data.data_source.books.BooksService
import com.example.bookstore.data.data_source.cart.dao.CartDao
import com.example.bookstore.data.data_source.cart.models.BookRoom
import com.example.bookstore.domain.model.Book

class RoomCartDataSource(
    private val cartDao: CartDao,
) {

    fun getAllBooks() = cartDao.getAll()

    suspend fun insert(book: Book) {
        cartDao.insert(mapBookToBookRoom(book))
    }

    //suspend fun remove(book: Book)

    suspend fun getNumberCartBook() = cartDao.getNumberCart()

    private fun mapBookToBookRoom(book: Book) = BookRoom(
        link = book.link,
    )
//    private fun mapBookRoomToBook(book: BookRoom) = Book(
//        title = book.link ?: "",
//        imageLink = null,
//        authors = listOf("abc", "abc"),
//        categories = listOf("abc", "abc"),
//        link = book.link
//    )
//
//    private suspend fun mapBookRoomToBookList(list: List<BookRoom>): List<Book> {
//        val _list: MutableList<Book> = mutableListOf()
//        for (it in list) {
//            _list.add((mapBookRoomToBook(it)))
//        }
//        return _list
//    }
}