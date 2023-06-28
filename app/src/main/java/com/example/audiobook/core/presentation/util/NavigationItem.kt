package com.example.audiobook.core.presentation.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val name : String ,
    val icon : ImageVector ,
    val route : String

)

val bottomNavigation = listOf(
    NavigationItem(
        name = "Home",
        icon = Icons.Default.Home,
        route = Routes.Home.screen
    ),
    NavigationItem(
        name = "Explore",
        icon = Icons.Default.Search,
        route = Routes.NonFunctionalExplore.screen
    ),
)