package com.example.presentation.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.presentation.account_screen.AccountScreen
import com.example.presentation.catalog_screen.CatalogScreen
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.detail_screen.DetailScreen
import com.example.presentation.favorite_screen.FavoriteScreen
import com.example.presentation.navigation.BottomNavigationBar
import com.example.presentation.navigation.NavigationItem
import com.example.presentation.navigation.NavigationObject
import com.example.presentation.navigation.NavigationObject.Companion.FOOD_ID_PARAM_KEY
import com.example.presentation.shopping_cart_screen.ShoppingCartScreen
import java.lang.IllegalStateException

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    showBottomBar = when (navBackStackEntry?.destination?.route) {
        "catalogScreen" -> true
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
    val viewModel: CatalogScreenViewModel = hiltViewModel()
    NavHost(navController, startDestination = NavigationItem.CatalogScreen.route) {
        composable(NavigationItem.CatalogScreen.route) {
            CatalogScreen(
                viewModel = viewModel,
                navigateToDetail = { foodId ->
                    navController.navigate(NavigationObject.DetailScreen.createRoute(foodId))
                },
                navigateToShoppingCart = {
                    navController.navigate(NavigationObject.ShoppingCartScreen.route)
                }
            )
        }

        composable(
            route = "${NavigationObject.DetailScreen.route}/{$FOOD_ID_PARAM_KEY}",
            arguments = listOf(navArgument(FOOD_ID_PARAM_KEY) { type = NavType.IntType })
        ) {
            val foodId = it.arguments?.getInt(FOOD_ID_PARAM_KEY) ?: throw IllegalStateException()
            DetailScreen(id = foodId, viewModel = viewModel) {
                navController.navigateUp()
            }
        }

        composable(NavigationObject.ShoppingCartScreen.route) {
            ShoppingCartScreen(
                viewModel = viewModel,
                navigateToDetail = { foodId ->
                    navController.navigate(NavigationObject.DetailScreen.createRoute(foodId))
                },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(NavigationItem.Favorite.route) {
            FavoriteScreen()
        }

        composable(NavigationItem.Account.route) {
            AccountScreen()
        }
    }
}