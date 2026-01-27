package com.mshell.shellfeed.ui.features.news_list

import android.content.res.Configuration
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
import com.mshell.shellfeed.ui.ui.theme.ShellFeedTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsListScreen(
    viewModel: NewsViewModel = koinViewModel(),
    onItemClick: (NewsDetail) -> Unit = {}
) {
    val newsState by viewModel.newsState.collectAsState()

    when(newsState) {
        is Resource.Success -> {
            val newsList = newsState.data
            if (newsList.isNullOrEmpty()) {
                return
            }

            NewsList(newsList, onItemClick = onItemClick)
        }
        else -> {}
    }
}

@Composable
fun NewsList(
    newsList: List<NewsDetail>,
    onItemClick: (NewsDetail) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = newsList,
            key = { it.url ?: ""},
        ) { news ->
            NewsItemCard(
                news,
                onClick = { onItemClick(news) }
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun NewsListScreenPreview() {
    val sampleNews = listOf(
        NewsDetail(
            source = Source(id = "1", name = "Source 1"),
            author = "Author 1",
            title = "Sample News Title 1",
            description = "This is a sample news description 1.",
            url = "https://example.com/news1",
            urlToImage = null,
            publishedAt = "2024-01-01T00:00:00Z",
            content = "Full content of the sample news article 1."
        ),
        NewsDetail(
            source = Source(id = "2", name = "Source 2"),
            author = "Author 2",
            title = "Sample News Title 2",
            description = "This is a sample news description 2.",
            url = "https://example.com/news2",
            urlToImage = null,
            publishedAt = "2024-01-02T00:00:00Z",
            content = "Full content of the sample news article 2."
        )
    )

    ShellFeedTheme {
        NewsList(newsList = sampleNews)
    }
}
