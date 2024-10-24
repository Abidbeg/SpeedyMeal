package com.am.speedymeal.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.speedymeal.model.BookListItem
import com.am.speedymeal.model.BookResponse
import com.am.speedymeal.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksModel @Inject constructor(
    private val repository: BookRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    var _strTopic = MutableStateFlow("Fiction")
    var strTopic = _strTopic.asStateFlow()

    val book: StateFlow<BookListItem>
        get() = repository.bookList

    init {
        viewModelScope.launch {
            repository.getBook(strTopic = strTopic.toString())
        }
    }

}