package com.example.bookstore.data.repository

import com.example.bookstore.data.data_source.books.BooksService
import com.example.bookstore.data.data_source.cart.RoomCartDataSource
import com.example.bookstore.data.data_source.cart.models.BookRoom
import com.example.bookstore.domain.model.Book
import com.example.bookstore.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CartRepositoryImpl(
    private val roomCartDataSource: RoomCartDataSource,
    private val booksService: BooksService
) : CartRepository {

    override fun getAllBooks(): Flow<List<Book>> {
        val bookRoomList = roomCartDataSource.getAllBooks()
        val flow = bookRoomList.map { list ->
            list.map { getBookFromLink(it) }
        }
        return flow
    }

    override suspend fun insertBook(book: Book) {
        roomCartDataSource.insert(book)
    }

    override suspend fun getNumberCartBook(): Int = roomCartDataSource.getNumberCartBook()

    override suspend fun remove(book: Book) {
        //roomCartDataSource.(BookRoom(book.link, book.uid))
    }

    private suspend fun getBookFromLink(bookRoom: BookRoom): Book {
        val item = booksService.getBookByLink(bookRoom.link)
        return Book(
            title = item.volumeInfo.title,
            imageLink = item.volumeInfo.imageLinks?.smallThumbnail,
            authors = item.volumeInfo.authors ?: listOf(),
            categories = item.volumeInfo.categories ?: listOf(),
            link = item.selfLink,
            uid = bookRoom.id
        )
    }

}
