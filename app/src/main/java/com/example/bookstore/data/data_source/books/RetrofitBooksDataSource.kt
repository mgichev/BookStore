package com.example.bookstore.data.data_source.books

import com.example.bookstore.domain.model.Book
import kotlin.random.Random

class RetrofitBooksDataSource(
    private val booksService: BooksService,
) : BooksDataSource {

    private val randomDefaultRequest = listOf(
        "спорт",
        "самообучение",
        "история",
        "бизнес",
        "english",
        "spanish",
        "город"
    )

    override suspend fun getBooks(title: String?, page: Int?): List<Book> {
        val request = title ?: randomDefaultRequest.random()
        val requestPage = page ?: Random.nextInt(0, 100)
        val response = booksService.getNextBookPage(request, requestPage)
        val bookList = mapItemToBookList(response.items ?: listOf())
        return bookList
    }

    override suspend fun getBooksByText(text: String): List<Book> {
        val response = booksService.getNextBookPage(text)
        val bookList = mapItemToBookList(response.items ?: listOf())
        return bookList
    }

    override suspend fun getBookByLink(link: String): Book {
        val item = booksService.getBookByLink(link)
        val book = Book(
            title = item.volumeInfo.title,
            imageLink = item.volumeInfo.imageLinks?.smallThumbnail,
            authors = item.volumeInfo.authors ?: listOf(),
            categories = item.volumeInfo.categories ?: listOf(),
            link = item.selfLink
        )
        return book
    }


    private fun mapItemToBookList(list: List<Item>): List<Book> {
        val bookList: MutableList<Book> = mutableListOf()
        for (it in list) {
            val book = Book(
                title = it.volumeInfo.title,
                imageLink = it.volumeInfo.imageLinks?.smallThumbnail,
                authors = it.volumeInfo.authors ?: listOf("no_author"),
                categories = it.volumeInfo.categories ?: listOf("no_category"),
                link = it.selfLink
            )
            bookList.add(book)
        }
        return bookList
    }
}