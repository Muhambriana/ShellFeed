package com.mshell.shellfeed.ui.features.news_list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mshell.shellfeed.core.data.source.Resource
import com.mshell.shellfeed.core.domain.model.NewsDetail
import com.mshell.shellfeed.core.domain.model.Source
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsListScreen(
    viewModel: NewsViewModel = koinViewModel(),
    onNewsClick: (NewsDetail) -> Unit = {}
) {
    val newsState by viewModel.newsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getTopHeadlines("us")
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Log.d("NewsListScreen", "Refresh button clicked")
                    viewModel.getTopHeadlines("us")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh"
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = newsState) {
                is Resource.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                        Text(
                            text = "Loading news...",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }

                is Resource.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Error: ${state.errorMessage}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                        Button(
                            onClick = { viewModel.getTopHeadlines("us") },
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            Text("Retry")
                        }
                    }
                }

                is Resource.Success -> {
                    val newsList = state.data
                    if (newsList.isNullOrEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No news available")
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(
                                items = newsList,
                                key = { it.url ?: it.title ?: "" }
                            ) { article ->
                                NewsItemCard(
                                    article = article,
                                    onClick = { onNewsClick(article) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NewsItemCard(article: NewsDetail, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = article.title ?: "No Title",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            article.description?.let { description ->
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            article.author?.let { author ->
                Text(
                    text = "By $author",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewsItemCardPreview() {
    MaterialTheme {
        NewsItemCard(
            article = NewsDetail(
                title = "Breaking News: Major Event Happening Now",
                description = "This is a sample description that gives more context about the news article and what happened in detail.",
                author = "John Doe",
                urlToImage = null,
                publishedAt = "2026-01-18T10:30:00Z",
                source = Source(name = "BBC News", id = "bbc-news"),
                url = "https://example.com/article",
                content = null
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Card with Image")
@Composable
fun NewsItemCardWithImagePreview() {
    MaterialTheme {
        NewsItemCard(
            article = NewsDetail(
                title = "Technology Breakthrough: New Discovery Changes Everything",
                description = "Scientists have made an incredible discovery that could revolutionize the way we live our daily lives.",
                author = "Jane Smith",
                urlToImage = "https://via.placeholder.com/400x300",
                publishedAt = "2026-01-18T14:20:00Z",
                source = Source(name = "TechCrunch", id = "techcrunch"),
                url = "https://example.com/article2",
                content = null
            ),
            onClick = {}
        )
    }
}
