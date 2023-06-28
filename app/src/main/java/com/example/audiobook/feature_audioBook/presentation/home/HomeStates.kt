package com.example.audiobook.feature_audioBook.presentation.home

import com.example.audiobook.core.domain.modal.Chapter
import com.example.audiobook.feature_audioBook.domain.modal.Book

data class HomeStates(
    val categorisedBookLoading : Boolean = false,
    val categorisedBook : List<Book> = emptyList(),
    val error : String = "",
    val selectedCategory : Int = 0,
    val selectedBook : Book? = null,
    val chapters : List<Chapter> = emptyList() ,
)
