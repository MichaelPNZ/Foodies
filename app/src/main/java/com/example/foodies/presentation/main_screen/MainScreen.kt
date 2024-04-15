package com.example.foodies.presentation.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodies.navigation.NavigationObject
import com.example.foodies.presentation.catalog_screen.CatalogScreen
import com.example.foodies.presentation.common.TopLine

@Composable
fun MainScreen() {
    val navController = rememberNavController()



    Scaffold(
        topBar = { TopLine() },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(
                    navController = navController
                )
            }
        },
    )
}

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController, startDestination = NavigationObject.CatalogScreen.route) {

        composable(NavigationObject.CatalogScreen.route) {
            CatalogScreen()
        }
    }
}