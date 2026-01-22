package com.mshell.shellfeed.ui.navigation

sealed class Screen(val route: String) {
    data object NewsList: Screen("news_list")
    data object NewsDetail: Screen("news_detail")

    companion object {
        const val NEWS_DETAIL_ARGUMENT = "news_detail_argument"
    }
}