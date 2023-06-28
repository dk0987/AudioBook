package com.example.audiobook.feature_audioBook.data.remote

import com.example.audiobook.core.data.util.StandardResponse
import com.example.audiobook.feature_audioBook.data.remote.dto.response.BookByCategoriesResponse
import com.example.audiobook.feature_audioBook.data.remote.dto.response.ChapterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookAPI {

    @GET("/api/book/book-category")
    suspend fun getBookByCategory(
        @Query("category") category : Int
    ) : StandardResponse<List<BookByCategoriesResponse>>

    @GET("/api/chapters")
    suspend fun getBookChapters(
        @Query("bookId") bookId : String
    ) : StandardResponse<List<ChapterResponse>>

    companion object{
        const val BASE_URL = "http://192.168.87.141:8080"
    }
}