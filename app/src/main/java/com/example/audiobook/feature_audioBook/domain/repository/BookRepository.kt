package com.example.audiobook.feature_audioBook.domain.repository

import com.example.audiobook.core.data.util.Resource
import com.example.audiobook.core.domain.modal.Chapter
import com.example.audiobook.feature_audioBook.domain.modal.Book

interface BookRepository {

    suspend fun getBookByCategory(
        category : Int
    ) : Resource<List<Book>>

    suspend fun getChapters(
        bookId : String
    ) : Resource<List<Chapter>>
}