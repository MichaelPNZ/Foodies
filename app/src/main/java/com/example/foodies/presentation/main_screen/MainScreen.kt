package com.example.foodies.presentation.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodies.navigation.NavigationObject
import com.example.foodies.presentation.catalog_screen.CatalogScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize()) {
        Navigation(
            navController = navController
        )
    }

//    Scaffold(
//        topBar = { TopLine() },
//        content = { padding ->
//            Box(modifier = Modifier.padding(padding)) {
//                Navigation(
//                    navController = navController
//                )
//            }
//        },
//    )
}

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController, startDestination = NavigationObject.CatalogScreen.route) {

        composable(NavigationObject.CatalogScreen.route) {
            CatalogScreen()
        }
    }
}