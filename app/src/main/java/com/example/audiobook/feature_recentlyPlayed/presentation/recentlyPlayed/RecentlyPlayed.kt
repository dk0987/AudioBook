package com.example.audiobook.feature_recentlyPlayed.presentation.recentlyPlayed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audiobook.core.presentation.component.BookItem

@ExperimentalFoundationApi
@Composable
fun RecentlyPlayed(

) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ){
        stickyHeader {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription =  ""
                    )
                }
                Text(
                    text = "Recently Played",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 2.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center ,
                    modifier = Modifier.fillMaxWidth(0.85f)
                )
            }
        }

        items(10){
            BookItem()
        }

        item {
            Spacer(modifier = Modifier.height(90.dp))
        }
    }
}