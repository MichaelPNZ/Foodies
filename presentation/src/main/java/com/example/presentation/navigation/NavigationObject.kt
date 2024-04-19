package com.example.presentation.navigation

import com.example.presentation.R


sealed class NavigationItem(val title: String, val route: String, var icon: Int) {
    data object CatalogScreen : NavigationItem("Catalog", "catalogScreen", R.drawable.home_icon)
    data object Favorite : NavigationItem("Favorite", "favorite", R.drawable.favorite_icon)
    data object Account : NavigationItem("Account", "account", R.drawable.person_icon)
}

sealed class NavigationObject(val route: String) {
    data object SplashScreen : NavigationObject("SplashScreen")
    data object ShoppingCartScreen : NavigationObject("ShoppingCart")
    data object DetailScreen : NavigationObject("DetailScreen") {
        fun createRoute(foodId: Int) = "$route/$foodId"
    }

    companion object {
        const val FOOD_ID_PARAM_KEY = "foodId"
    }
}