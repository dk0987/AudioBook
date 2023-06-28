package com.example.audiobook.feature_audioBook.data.mapper

import com.example.audiobook.feature_audioBook.data.remote.dto.response.BookByCategoriesResponse
import com.example.audiobook.feature_audioBook.domain.modal.Book
import java.sql.Timestamp

fun BookByCategoriesResponse.toBook() : Book {
    return Book(
        _id = _id ,
        uploadedBy = uploadedBy,
        bookName = bookName,
        author = author ,
        uploaded = uploaded ,
        description = description,
        favourites = favourites,
        category = category,
        cover = cover
    )
}