package com.sofit.israr.composeproject.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sofit.israr.composeproject.ui.screens.ProductDetailsScreen
import com.sofit.israr.composeproject.ui.screens.IndexScreen
import com.sofit.israr.composeproject.viewModels.IndexViewModel

@Composable
fun MainNavigation(vm : IndexViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.IndexScreen.name){
        composable(route = Screens.IndexScreen.name){
            IndexScreen(navController = navController, vm)
        }

        composable(
            route = Screens.DetailsScreen.name+"/{productId}",
            arguments = listOf(navArgument(name = "productId") {type = NavType.IntType})
        ){ backStackEntry ->
            ProductDetailsScreen(navController = navController, vm, backStackEntry.arguments?.getInt("productId")?:1)
        }
    }
}

enum class Screens {
    IndexScreen,
    DetailsScreen
}