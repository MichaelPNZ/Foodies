package com.example.presentation.main_screen

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.auth.GoogleAuthUIClient
import com.example.auth.SignInViewModel
import com.example.presentation.catalog_screen.CatalogScreen
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.detail_screen.DetailScreen
import com.example.presentation.favorite_screen.FavoriteScreen
import com.example.presentation.login_screen.LoginScreen
import com.example.presentation.login_screen.LoginScreenViewModel
import com.example.presentation.navigation.BottomNavigationBar
import com.example.presentation.navigation.NavigationItem
import com.example.presentation.navigation.NavigationObject
import com.example.presentation.navigation.NavigationObject.Companion.FOOD_ID_PARAM_KEY
import com.example.presentation.navigation.NavigationObject.Companion.SHOPPING_CART_INDEX_PARAM_KEY
import com.example.presentation.personal_account_screen.OrderDetailScreen
import com.example.presentation.personal_account_screen.PersonalAccountScreen
import com.example.presentation.personal_account_screen.PersonalAccountViewModel
import com.example.presentation.shopping_cart_screen.ShoppingCartScreen
import com.example.presentation.splash_screen.SplashScreen
import kotlinx.coroutines.launch

@Composable
fun MainScreen(googleAuthUIClient: GoogleAuthUIClient) {
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
                    googleAuthUIClient = googleAuthUIClient,
                    navController = navController
                )
            }
        },
    )
}

@Composable
fun Navigation(googleAuthUIClient: GoogleAuthUIClient, navController: NavHostController) {
    val catalogViewModel: CatalogScreenViewModel = hiltViewModel()
    val personalAccountViewModel: PersonalAccountViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    NavHost(navController, startDestination = NavigationObject.LoginScreen.route) {
        composable(NavigationObject.LoginScreen.route) {
            val loginViewModel = hiltViewModel<LoginScreenViewModel>()
            val authViewModel = viewModel<SignInViewModel>()
            val state by authViewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit) {
                val user = googleAuthUIClient.getSignedInUser()
                if (user != null) {
                    user.userId?.let { it1 -> loginViewModel.saveIsLoginStatus(it1) }
                    navController.navigate(NavigationItem.CatalogScreen.route)
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        scope.launch {
                            val signInResult = googleAuthUIClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            authViewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    val user = googleAuthUIClient.getSignedInUser()
                    if (user != null) {
                        Toast.makeText(
                            navController.context,
                            "Sign in successful",
                            Toast.LENGTH_LONG
                        ).show()

                        user.userId?.let { id ->
                            loginViewModel.saveUser(id)
                            loginViewModel.saveIsLoginStatus(id)

                            navController.navigate(NavigationItem.CatalogScreen.route)
                            authViewModel.resetState()
                        }
                    }
                }
            }

            LoginScreen(
                state = state,
                onSignInClick = {
                    scope.launch {
                        val signInIntentSender = googleAuthUIClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                },
                navigateToCatalogScreen = {
                    navController.navigate(NavigationItem.CatalogScreen.route)
                }
            )
        }

        composable(NavigationObject.SplashScreen.route) {
            SplashScreen(
                navigateToCatalogScreen = {
                    navController.navigate(NavigationItem.CatalogScreen.route)
                }
            )
        }

        composable(NavigationItem.CatalogScreen.route) {
            CatalogScreen(
                viewModel = catalogViewModel,
                navigateToDetail = { foodId ->
                    navController.navigate(NavigationObject.DetailScreen.createRoute(foodId)) {
                        popUpTo(NavigationItem.CatalogScreen.route) { inclusive = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navigateToShoppingCart = {
                    navController.navigate(NavigationObject.ShoppingCartScreen.route) {
                        popUpTo(NavigationItem.CatalogScreen.route) { inclusive = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }

        composable(
            route = "${NavigationObject.DetailScreen.route}/{$FOOD_ID_PARAM_KEY}",
            arguments = listOf(navArgument(FOOD_ID_PARAM_KEY) { type = NavType.IntType })
        ) {
            val foodId = it.arguments?.getInt(FOOD_ID_PARAM_KEY) ?: throw IllegalStateException()
            DetailScreen(id = foodId, viewModel = catalogViewModel) {
                navController.navigateUp()
            }
        }

        composable(NavigationObject.ShoppingCartScreen.route) {
            ShoppingCartScreen(
                viewModel = catalogViewModel,
                navigateToDetail = { foodId ->
                    navController.navigate(NavigationObject.DetailScreen.createRoute(foodId))
                },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(NavigationItem.Favorite.route) {
            FavoriteScreen(
                viewModel = catalogViewModel,
                navigateToDetail = { foodId ->
                    navController.navigate(NavigationObject.DetailScreen.createRoute(foodId))
                },
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(NavigationItem.Account.route) {
            PersonalAccountScreen(
                userData = googleAuthUIClient.getSignedInUser(),
                onSignOutClick = {
                    scope.launch {
                        googleAuthUIClient.signOut()
                        Toast.makeText(
                            navController.context,
                            "Sign out",
                            Toast.LENGTH_LONG
                        ).show()
                        navController.navigate(NavigationObject.LoginScreen.route)
                    }
                },
                navigateToSoppingCartDetail = { shoppingCartIndex ->
                    navController.navigate(
                        NavigationObject.OrderDetailScreen.createRoute(shoppingCartIndex)
                    )
                }
            )
        }

        composable(
            route = "${NavigationObject.OrderDetailScreen.route}/{$SHOPPING_CART_INDEX_PARAM_KEY}",
            arguments = listOf(navArgument(SHOPPING_CART_INDEX_PARAM_KEY) { type = NavType.IntType })
        ) {
            val shoppingCartIndex = it.arguments?.getInt(SHOPPING_CART_INDEX_PARAM_KEY)
                ?: throw IllegalStateException()
            OrderDetailScreen(
                shoppingCartIndex = shoppingCartIndex,
                viewModel = personalAccountViewModel,
            ) {
                navController.navigateUp()
            }
        }
    }
}