package com.example.audiobook.core.exoplayer.util

import androidx.core.os.bundleOf
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Metadata
import com.example.audiobook.core.domain.modal.Chapter

internal fun buildPlayableMediaItem(
    chapter : Chapter
) = MediaItem.Builder()
    .setMediaId(chapter.id)
    .setRequestMetadata(
        MediaItem.RequestMetadata.Builder()
            .setMediaUri(chapter.chapterUri)
            .build()
    )
    .setMediaMetadata(
        MediaMetadata.Builder()
            .setArtworkUri(chapter.bookCoverUri)
            .setTitle(chapter.chapterTitle)
            .setArtist(chapter.bookAuthor)
            .setFolderType(MediaMetadata.FOLDER_TYPE_NONE)
            .setIsPlayable(true)
            .setExtras(bundleOf("ALBUM ID" to chapter.bookId  , "ALBUM NAME" to chapter.bookTitle))
            .build()
    )
    .build()