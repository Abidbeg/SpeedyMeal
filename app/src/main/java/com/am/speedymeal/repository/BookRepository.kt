package com.am.speedymeal.repository

import android.util.Log
import com.am.speedymeal.api.BooksApi
import com.am.speedymeal.model.BookListItem
import com.am.speedymeal.model.BookResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class BookRepository @Inject constructor(private val booksApi: BooksApi) {

    private val _book = MutableStateFlow<BookListItem>(BookListItem(0,"","", emptyList()))
    val bookList: StateFlow<BookListItem>
        get() = _book

    suspend fun getBook() {
        val response = booksApi.getBookList("image/jpeg", 1, "Romeo")
        if (response!=null) {
            _book.emit(response)
        }
    }
}