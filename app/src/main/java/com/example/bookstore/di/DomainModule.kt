package com.example.bookstore.di

import com.example.bookstore.domain.use_cases.GetBookByLinkUseCase
import com.example.bookstore.domain.use_cases.GetBookListByTextUseCase
import com.example.bookstore.domain.use_cases.GetBookListUseCase
import com.example.bookstore.domain.use_cases.GetCartListUseCase
import com.example.bookstore.domain.use_cases.GetNumberCartBookUseCase
import com.example.bookstore.domain.use_cases.InsertBookCartListUseCase
import com.example.bookstore.domain.use_cases.RemoveBookInCartUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetBookListUseCase(booksRepository = get())
    }

    factory {
        GetCartListUseCase(cartRepository = get())
    }

    factory {
        InsertBookCartListUseCase(cartRepository = get())
    }

    factory {
        GetBookListByTextUseCase(
            booksRepository = get()
        )
    }

    factory {
        GetNumberCartBookUseCase(
            cartRepository = get()
        )
    }

    factory {
        GetBookByLinkUseCase(
            booksRepository = get()
        )
    }

    factory {
        RemoveBookInCartUseCase(
            cartRepository = get()
        )
    }
}