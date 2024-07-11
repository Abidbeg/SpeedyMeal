package com.am.speedymeal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.am.speedymeal.screens.HomeScreen
import com.am.speedymeal.screens.MyAccountScreen
import com.am.speedymeal.ui.theme.SpeedyMealTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SpeedyMealTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(bottomBar = {
        BottomAppBar(modifier = Modifier) {
            BottomNavigationBar(navController)
        }
    }) { innerPadding ->
        Box(
            modifier = Modifier.padding(
                PaddingValues(
                    0.dp,
                    0.dp,
                    0.dp,
                    innerPadding.calculateBottomPadding()
                )
            )
        ) {
            Navigations(navController)
        }
    }
}

@Composable
fun Navigations(navController: NavHostController) {
    NavHost(navController, NavigationItem.HomeScreen.route) {
        composable(NavigationItem.HomeScreen.route)
        {
            HomeScreen()
        }
        composable(NavigationItem.Profile.route)
        {
            MyAccountScreen(modifier = Modifier)
        }
    }
}

@Composable
fun BottomNavigationBar(navControl: NavHostController) {

    val items = listOf(NavigationItem.HomeScreen, NavigationItem.Profile)

    var selectedItem by remember {
        mutableStateOf(0)
    }
    var currentRoute by remember {
        mutableStateOf(NavigationItem.HomeScreen.route)
    }


    items.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(item.image!!, contentDescription = item.lable) },
                label = { Text(item.lable) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    currentRoute = item.route
                    navControl.navigate(item.route) {
                        navControl.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

}