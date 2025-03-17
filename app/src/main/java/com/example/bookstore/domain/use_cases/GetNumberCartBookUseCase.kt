package com.example.bookstore.domain.use_cases

import com.example.bookstore.domain.repository.CartRepository

class GetNumberCartBookUseCase(private val cartRepository: CartRepository) {

    suspend fun execute() : Int = cartRepository.getNumberCartBook()

}