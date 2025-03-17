package com.example.bookstore.domain.model

data class Book(
    val title: String,
    val imageLink: String?,
    val authors: List<String>,
    val categories: List<String>,
    val link: String?,
    val uid: Long? = null,
    val type: String = "Только книжный вариант"
) {
    constructor(title: String): this(title, null, listOf(), listOf(), null)
}
