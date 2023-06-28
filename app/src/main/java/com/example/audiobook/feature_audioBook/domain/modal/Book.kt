package com.example.audiobook.feature_audioBook.domain.modal

import java.sql.Timestamp

data class Book(
    val _id : String,
    val uploadedBy : String,
    val bookName : String,
    val author : String,
    val uploaded : Long,
    val description : String,
    val favourites : Int,
    val category : List<String>,
    val cover : String
)
