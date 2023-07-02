package com.example.audiobook.core.presentation.util

sealed class Routes(val screen : String){
    object Login : Routes("Login Screen")
    object Splash : Routes("Splash Screen")
    object Register : Routes("Register Screen")
    object Home : Routes("Home Screen")
    object RecentlyPlayed : Routes("RecentlyPlayed Screen")
    object Favourites : Routes("Favourites Screen")
    object Profile : Routes("Profile Screen")
    object FunctionalExplore : Routes("Functional Explore Screen")
    object NonFunctionalExplore : Routes("Non Functional Explore Screen")
}