package com.example.audiobook.feature_audioBook.presentation.home

import com.example.audiobook.feature_audioBook.domain.modal.Book

sealed class HomeEvent{
    data class OnCategoryEvent (val value : Int) : HomeEvent()
    data class OnBookDetailEvent (val book : Book) : HomeEvent()
    data class OnPlay(val bookId : String) : HomeEvent()
}

