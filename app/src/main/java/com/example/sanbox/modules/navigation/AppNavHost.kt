package com.example.sanbox.modules.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sanbox.modules.patterns.presenter.PatternsScreen
import com.example.sanbox.modules.ecdsa.presenter.EcdsaScreen
import com.example.sanbox.modules.fetchdata.presenter.FetchDataScreen
import com.example.sanbox.modules.grpc.presenter.GrpcScreen
import com.example.sanbox.modules.home.HomeScreen
import com.example.sanbox.modules.others.presenter.OthersScreen
import com.example.sanbox.modules.wear.presenter.WearScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToDetail = { id ->
                    navController.navigate(Screen.Detail.createRoute(id))
                }
            )
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt("id") ?: 0

            when (val module = Modules.fromId(id)) {
                Modules.GRPC -> GrpcScreen(module)
                Modules.PATTERNS -> PatternsScreen()
                Modules.OTHERS -> OthersScreen()
                Modules.FETCH_DATA -> FetchDataScreen()
                Modules.ECDSA -> EcdsaScreen()
                Modules.WEAROS -> WearScreen()
                else -> {}
            }
        }
    }
}