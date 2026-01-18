package com.mshell.shellfeed.ui.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mshell.shellfeed.core.domain.model.NewsDetail
import com.mshell.shellfeed.ui.features.news_detail.NewsDetailScreen
import com.mshell.shellfeed.ui.features.news_list.NewsListScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    
    NavHost(
        navController = navController,
        startDestination = Screen.NewsList.route
    ) {
        composable(Screen.NewsList.route) {
            NewsListScreen(
                onNewsClick = { newsDetail ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        Screen.NEWS_DETAIL_ARGUMENT,
                        newsDetail
                    )
                    navController.navigate(Screen.NewsDetail.route)
                }
            )
        }

        composable(Screen.NewsDetail.route) {
            val newsDetail = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<NewsDetail>(Screen.NEWS_DETAIL_ARGUMENT)

            newsDetail?.let { news ->
                NewsDetailScreen(
                    newsDetail = news,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onShareClick = {
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "${news.title}\n\n${news.url}")
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "Share news"))
                    }
                )
            }
        }
    }
}
