package com.example.audiobook.feature_audioBook.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audiobook.R

@Composable
fun HomeTab(
    onProfile : () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Hey IAmDK",
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 2.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Start
            )
            Text(
                text = "Good Morning",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Start
            )
        }

        Image(
            painter = painterResource(id = R.drawable.profiel_pic),
            contentDescription = "Google",
            modifier = Modifier
                .size(40.dp)
                .padding(2.dp)
                .clip(CircleShape)
                .shadow(10.dp)
                .clickable {
                       onProfile()
                },
            contentScale = ContentScale.Crop
        )
    }
}