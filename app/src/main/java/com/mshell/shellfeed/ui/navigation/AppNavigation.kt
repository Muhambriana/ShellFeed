package com.mshell.shellfeed.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mshell.shellfeed.ui.features.news_list.NewsListScreen

@Composable
fun AppNavigation(
    navHostController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current

    NavHost(
        navController = navHostController,
        startDestination = Screen.NewsList.route
    ) {
        composable(Screen.NewsList.route) {
            NewsListScreen()
        }

        composable(Screen.NewsDetail.route) {
            val newsDetail = navHostController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Screen.NewsDetail>(Screen.NEWS_DETAIL_ARGUMENT)

            newsDetail?.let { news ->
                // TODO
            }
        }
    }
}