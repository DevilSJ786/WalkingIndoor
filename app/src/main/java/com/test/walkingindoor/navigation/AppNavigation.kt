package com.test.walkingindoor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.walkingindoor.screens.goals.GoalsScreen
import com.test.walkingindoor.screens.home.HomeScreen
import com.test.walkingindoor.screens.steps_counter.StepsCounterScreen
import com.test.walkingindoor.screens.walking_types.WalkingtypesScreen
import com.test.walkingindoor.viewmodel.HomeViewModel

@Composable
fun AppNavigation(homeViewModel: HomeViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.name) {
        composable(AppScreens.HomeScreen.name) {
           HomeScreen(navController = navController, homeViewModel)
        }
        composable(AppScreens.WalkingtypesScreen.name) {
            WalkingtypesScreen(navController = navController,homeViewModel)
        }
        composable(AppScreens.GoalsScreen.name) {
            GoalsScreen(navController = navController,homeViewModel)
        }
        composable(AppScreens.StepsCounterScreen.name){
            StepsCounterScreen(navController=navController)
        }

    }
}