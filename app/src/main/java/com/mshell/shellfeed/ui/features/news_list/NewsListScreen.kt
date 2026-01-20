package com.mshell.shellfeed.ui.features.news_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mshell.shellfeed.core.data.source.Resource
import com.mshell.shellfeed.core.domain.model.NewsDetail
import com.mshell.shellfeed.core.domain.model.Source

@Composable
fun NewsListScreen(
    viewModel: NewsViewModel
) {
    val newsState by viewModel.newsState.collectAsState()

    when(newsState) {
        is Resource.Success -> {
            val newsList = newsState.data
            if (newsList.isNullOrEmpty()) {

                return
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = newsList,
                    key = { it.url ?: ""}
                ) { news ->
                    NewsItemCard(news)
                }
            }
        }
        else -> {}
    }
}
