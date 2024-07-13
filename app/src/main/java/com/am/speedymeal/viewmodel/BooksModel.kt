package com.am.speedymeal.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.speedymeal.model.BookListItem
import com.am.speedymeal.model.BookResponse
import com.am.speedymeal.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksModel @Inject constructor(
    private val repository: BookRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val book: StateFlow<BookListItem>
        get() = repository.bookList

    init {
        viewModelScope.launch {
            repository.getBook()
        }
    }

}