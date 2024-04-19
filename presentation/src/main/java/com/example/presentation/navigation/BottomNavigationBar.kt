package com.example.presentation.navigation


import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    var selectedItem by remember { mutableIntStateOf(
        when (currentRoute) {
            NavigationItem.CatalogScreen.route -> 0
            NavigationItem.Favorite.route -> 1
            NavigationItem.Account.route -> 2
            else -> 0
        }
    )
    }

    val items = listOf(
        NavigationItem.CatalogScreen,
        NavigationItem.Favorite,
        NavigationItem.Account
    )
    NavigationBar(
        containerColor = com.example.presentation.theme.GrayBg,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                selected = selectedItem == index,
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    selectedIconColor = com.example.presentation.theme.Primary,
                    selectedTextColor = com.example.presentation.theme.Primary,
                    indicatorColor = Color.Transparent
                ),
                onClick = {
                    if (selectedItem != index) {
                        selectedItem = index
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}