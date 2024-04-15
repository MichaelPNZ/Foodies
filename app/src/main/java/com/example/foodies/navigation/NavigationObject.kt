package com.example.foodies.navigation

sealed class NavigationObject(val route: String) {
    data object CatalogScreen : NavigationObject("CatalogScreen")
}