package com.jj.clickerproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jj.clickerproject.navigation.model.GraphNavigation
import com.jj.clickerproject.navigation.model.Route
import com.jj.clickerproject.presentation.ui.main.MainScreen
import com.jj.clickerproject.presentation.ui.main.MainScreenViewModel
import com.jj.clickerproject.presentation.ui.secondary.SecondaryScreen
import com.jj.clickerproject.presentation.ui.secondary.SecondaryScreenViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MainNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Route.MainScreen.route,
        route = GraphNavigation.MainNavGraph.route,
    ) {
        composable(
            route = Route.MainScreen.route,
        ) {
            val viewModel: MainScreenViewModel = getViewModel()
            MainScreen(
                navController = navController,
                viewModel = viewModel,
            )
        }
        composable(
            route = Route.SecondaryScreen.route,
        ) {
            val viewModel: SecondaryScreenViewModel = getViewModel()
            SecondaryScreen(
                viewModel = viewModel,
            )
        }
    }
}