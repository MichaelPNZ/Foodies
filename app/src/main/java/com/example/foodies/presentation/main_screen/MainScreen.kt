package com.example.foodies.presentation.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodies.domain.model.Product
import com.example.foodies.navigation.BottomNavigationBar
import com.example.foodies.navigation.NavigationItem
import com.example.foodies.navigation.NavigationObject
import com.example.foodies.presentation.account_screen.AccountScreen
import com.example.foodies.presentation.catalog_screen.CatalogScreen
import com.example.foodies.presentation.detail_screen.DetailScreen
import com.example.foodies.presentation.favorite_screen.FavoriteScreen
import com.example.foodies.presentation.shopping_cart_screen.ShoppingCartScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    showBottomBar = when (navBackStackEntry?.destination?.route) {
        "catalogScreen" -> true
        "shoppingCart" -> true
        "favorite" -> true
        "account" -> true
        else -> false
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) BottomNavigationBar(navController)
        },
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

    NavHost(navController, startDestination = NavigationItem.CatalogScreen.route) {

        composable(NavigationItem.CatalogScreen.route) {
            CatalogScreen(
                navigateToDetail = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("product", it)
                    navController.navigate(NavigationObject.DetailScreen.route)
                }
            )
        }

        composable(NavigationObject.DetailScreen.route) {
            val product =
                navController.previousBackStackEntry?.savedStateHandle?.get<Product>("product")
            if (product != null) {
                DetailScreen(product) {
                    navController.navigateUp()
                }
            }
        }

        composable(NavigationItem.ShoppingCartScreen.route) {
            ShoppingCartScreen()
        }

        composable(NavigationItem.Favorite.route) {
            FavoriteScreen()
        }

        composable(NavigationItem.Account.route) {
            AccountScreen()
        }
    }
}