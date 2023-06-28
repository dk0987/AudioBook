package com.example.audiobook.core.exoplayer.notification

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.audiobook.R

@SuppressLint("PrivateResource")
object AppIcons {
    val SkipPrevious = Icon.DrawableResourceIcon(androidx.media3.ui.R.drawable.exo_icon_previous)
    val Play = Icon.DrawableResourceIcon(androidx.media3.ui.R.drawable.exo_icon_play)
    val Pause = Icon.DrawableResourceIcon(androidx.media3.ui.R.drawable.exo_icon_pause)
    val SkipNext = Icon.DrawableResourceIcon(androidx.media3.ui.R.drawable.exo_ic_skip_next)
    val Music = Icon.DrawableResourceIcon(R.drawable.logo)
}

sealed interface Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon
    data class DrawableResourceIcon(@DrawableRes val resourceId: Int) : Icon
}