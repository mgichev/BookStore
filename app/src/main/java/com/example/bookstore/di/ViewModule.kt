package com.example.bookstore.di

import com.example.bookstore.ui.screens.account_screen.AccountViewModel
import com.example.bookstore.ui.screens.cart_screen.CartListViewModel
import com.example.bookstore.ui.screens.book_screen.BookPageViewModel
import com.example.bookstore.ui.screens.library_screen.BooksViewModel
import com.example.bookstore.ui.screens.search_screen.SearchBooksViewModel
import com.example.bookstore.ui.screens.shop_screen.viewmodel.ShopBooksViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        BooksViewModel(getBookListUseCase = get())
    }

    viewModel {
        ShopBooksViewModel(
            getBookListUseCase = get(),
            insertBookCartListUseCase = get(),
        )
    }

    viewModel {
        CartListViewModel(
            getCartListUseCase = get(),
            removeBookInCartUseCase = get()
        )
    }

    viewModel {
        SearchBooksViewModel(
            getBookListByTextUseCase = get(),
            insertBookCartListUseCase = get()
        )
    }

    viewModel {
        AccountViewModel(
            getNumberCartBookUseCase = get()
        )
    }

    viewModel {
        BookPageViewModel(
            getBookByLinkUseCase = get()
        )
    }

}