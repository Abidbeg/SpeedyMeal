package com.am.speedymeal

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String,val image: ImageVector,val lable: String) {
    object HomeScreen : NavigationItem("HomeScreen", Icons.Default.Home, "homeScreen")
    object Profile : NavigationItem("Profile", Icons.Default.AccountCircle, "profile")
}