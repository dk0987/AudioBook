package com.example.audiobook.feature_audioBook.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.audiobook.R

@Composable
fun RecentlyPlayedCard() {
    Box(
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
            .border(width = 2.dp, Color(0xffff8833), CircleShape)
            .padding(5.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.dabi),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        )
    }
}