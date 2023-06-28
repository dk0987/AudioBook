package com.example.audiobook.core.presentation.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceEvenly
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.audiobook.core.presentation.util.NavigationItem
import com.example.audiobook.core.presentation.util.bottomNavigation

@ExperimentalMaterial3Api
@Composable
fun StandardScaffold(
    navController: NavController,
    isVisible : Boolean,
    navItem : List<NavigationItem> = bottomNavigation,
    content : (@Composable (PaddingValues) -> Unit),
    snackBarHostState: SnackbarHostState,
    animatedColor : Color
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState){
                Snackbar(
                    snackbarData = it ,
                    containerColor = MaterialTheme.colorScheme.primary ,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        bottomBar = {
            if (isVisible){
                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.background
                                ),
                                startY = 0.1f
                            )
                        )
                    ,
                    containerColor = Color.Transparent,
                    contentColor = Color.Transparent,
                    tonalElevation = 0.dp,

                ) {
                    Row(modifier = Modifier.fillMaxWidth().background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background
                            ),
                            startY = 0.1f
                        )
                    ) , horizontalArrangement = Arrangement.SpaceAround , verticalAlignment = Alignment.CenterVertically) {
                        navItem.forEach { item ->
                            Column(
                                horizontalAlignment = CenterHorizontally ,
                                verticalArrangement = SpaceEvenly,
                                modifier = Modifier
                                    .clickable {
                                        if (navController.currentDestination?.route == item.route) Unit else {
                                            navController.popBackStack()
                                            navController.navigate(item.route)
                                        }
                                    }
                            ){
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = "Icon" ,
                                    tint = if (navController.currentDestination?.route == item.route) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.tertiary,
                                )
                                Text(
                                    text = item.name,
                                    fontSize = 10.sp,
                                    letterSpacing = 2.sp ,
                                    fontWeight = FontWeight.Normal ,
                                    textAlign = TextAlign.Center ,
                                    color = if (navController.currentDestination?.route  == item.route) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.tertiary
                                )
                            }
                        }
                    }

                }
            }
        }
    ){
        content(it )
    }
}
