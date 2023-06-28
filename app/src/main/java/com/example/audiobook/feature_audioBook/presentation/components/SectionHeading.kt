package com.example.audiobook.feature_audioBook.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalMaterial3Api
@Composable
fun SectionHeading(
    heading : String,
    modifier: Modifier = Modifier ,
    selected : Boolean = false ,
    onClick : () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (!selected) Color(0xff292929) else MaterialTheme.colorScheme.onPrimary,
            contentColor = if (!selected) MaterialTheme.colorScheme.onPrimary else Color.Black,
        ),
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = modifier.padding(start = 20.dp),
        onClick = onClick
    ) {
        Text(
            text = heading,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 1.sp,
            color = if (!selected) MaterialTheme.colorScheme.onPrimary else Color(0xff292929),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp).clickable { onClick() }
        )
    }
}