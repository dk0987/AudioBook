package com.example.audiobook.core.exoplayer.notification

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.audiobook.R

internal suspend fun Uri.asArtworkBitmap(context: Context): Bitmap? {
    val loader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(this)
        .placeholder(R.drawable.logo)
        .error(R.drawable.logo)
        .build()

    val drawable = loader.execute(request).drawable
    return drawable?.toBitmap()
}