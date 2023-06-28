package com.example.audiobook.core.domain.modal

import android.net.Uri


data class Chapter(
    val id : String,
    val bookId : String,
    val chapterUri : Uri,
    val bookCoverUri : Uri,
    val chapterTitle : String,
    val bookAuthor : String,
    val bookTitle : String
)
