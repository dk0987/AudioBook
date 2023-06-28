package com.example.audiobook.core.exoplayer.mapper

import android.net.Uri
import androidx.media3.common.MediaItem
import com.example.audiobook.core.domain.modal.Chapter
import com.example.audiobook.core.exoplayer.util.buildPlayableMediaItem

fun Chapter.asMediaItem() = buildPlayableMediaItem(
    Chapter(
        id = id ,
        bookId = bookId,
        chapterUri = chapterUri,
        bookCoverUri = bookCoverUri ,
        chapterTitle = chapterTitle ,
        bookAuthor = bookAuthor ,
        bookTitle = bookTitle
    )
)

fun MediaItem.asChapter() = Chapter(
    id = mediaId,
    chapterUri = requestMetadata.mediaUri ?: Uri.EMPTY ,
    bookCoverUri = mediaMetadata.artworkUri ?: Uri.EMPTY ,
    chapterTitle = mediaMetadata.title?.toString() ?: "" ,
    bookAuthor = mediaMetadata.artist?.toString() ?: "",
    bookId = mediaMetadata.extras?.getString("ALBUM ID") ?: "",
    bookTitle = mediaMetadata.extras?.getString("ALBUM NAME") ?: "" ,
)