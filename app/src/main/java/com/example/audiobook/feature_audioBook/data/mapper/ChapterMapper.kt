package com.example.audiobook.feature_audioBook.data.mapper

import androidx.core.net.toUri
import com.example.audiobook.core.domain.modal.Chapter
import com.example.audiobook.feature_audioBook.data.remote.dto.response.ChapterResponse

fun ChapterResponse.asChapter() = Chapter(
    id = id,
    bookId = bookId,
    chapterUri = url.toUri() ,
    bookCoverUri = bookCover.toUri() ,
    chapterTitle = chapterName ,
    bookAuthor = bookAuthor ,
    bookTitle = bookName
)

fun Chapter.asChapterResponse() = ChapterResponse(
    id = id,
    bookId = bookId ,
    url =     chapterUri.toString()  ,
    bookCover = bookCoverUri.toString() ,
    chapterName  = chapterTitle  ,
    bookAuthor = bookAuthor  ,
    bookName = bookTitle  ,
    chapterNumber = 0
)