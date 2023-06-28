package com.example.audiobook.feature_audioBook.data.remote.dto.response

data class ChapterResponse(
    val id : String,
    val chapterNumber: Int,
    val chapterName: String,
    val url: String,
    val bookCover: String,
    val bookName: String,
    val bookAuthor: String,
    val bookId: String,
)
