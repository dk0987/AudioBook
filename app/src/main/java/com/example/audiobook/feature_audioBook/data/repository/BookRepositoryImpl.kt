package com.example.audiobook.feature_audioBook.data.repository

import com.example.audiobook.core.data.util.Resource
import com.example.audiobook.core.domain.modal.Chapter
import com.example.audiobook.feature_audioBook.data.mapper.asChapter
import com.example.audiobook.feature_audioBook.data.mapper.asChapterResponse
import com.example.audiobook.feature_audioBook.data.mapper.toBook
import com.example.audiobook.feature_audioBook.data.remote.BookAPI
import com.example.audiobook.feature_audioBook.data.remote.dto.response.ChapterResponse
import com.example.audiobook.feature_audioBook.domain.modal.Book
import com.example.audiobook.feature_audioBook.domain.repository.BookRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepositoryImpl @Inject constructor(
    private val bookAPI : BookAPI
) : BookRepository {

    override suspend fun getBookByCategory(
        category: Int
    ): Resource<List<Book>> {
        return try {
            val response = bookAPI.getBookByCategory(category)
            if (response.succesfull){
                Resource.Success(response.result.data?.map {it.toBook()})
            } else {
                Resource.Error(response.result.message)
            }
        } catch (err : IOException){
            Resource.Error("Something went wrong")
        } catch (err : HttpException){
            Resource.Error("Something went wrong")
        }

    }

    override suspend fun getChapters(bookId: String): Resource<List<Chapter>> {
        return try {
            val response = bookAPI.getBookChapters(bookId)
            if (response.succesfull){
                Resource.Success(response.result.data?.map(ChapterResponse::asChapter))
            } else {
                Resource.Error(response.result.message)
            }
        } catch (err : IOException){
            Resource.Error("Something went wrong")
        } catch (err : HttpException){
            Resource.Error("Something went wrong")
        }
    }
}