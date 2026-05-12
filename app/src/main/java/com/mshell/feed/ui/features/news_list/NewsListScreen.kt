package com.mshell.feed.ui.features.news_list

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mshell.feed.R
import com.mshell.feed.core.data.source.Resource
import com.mshell.feed.core.domain.model.NewsDetail
import com.mshell.feed.core.domain.model.Source
import com.mshell.feed.ui.ui.theme.ShellFeedTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    viewModel: NewsViewModel = koinViewModel(),
    onItemClick: (NewsDetail) -> Unit = {}
) {
    val newsState by viewModel.newsState.collectAsState()
    val scrollBehaviors = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehaviors.nestedScrollConnection),
            containerColor = Color.Transparent,
            topBar = {
                NewsListTopBar(scrollBehaviors)
            }
        ) { paddingValues ->
            when(newsState) {
                is Resource.Success -> {
                    val newsList = newsState.data
                    if (newsList.isNullOrEmpty()) {
                        return@Scaffold
                    }

                    NewsList(
                        modifier = Modifier.padding(paddingValues),
                        newsList,
                        onItemClick = onItemClick
                    )
                }
                else -> {}
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListTopBar(
    scrollBehavior: TopAppBarScrollBehavior
) {
    var expanded by remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = MaterialTheme.colorScheme.surface,
        ),
        scrollBehavior = scrollBehavior,
        title = { stringResource(R.string.app_name) },
        actions = {
            IconButton(
                onClick = { expanded = true }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More"
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {

                }
            ) {
                DropdownMenuItem(
                    text = {
                        Text("Contact Us")
                    },
                    onClick = {
                        uriHandler.openUri("https://sites.google.com/view/shellfeed-contact/home")
                        expanded = false
                    }
                )

                DropdownMenuItem(
                    text = {
                        Text("Privacy Policy")
                    },
                    onClick = {
                        uriHandler.openUri("https://sites.google.com/view/privacy-policy-shell-feed/home")
                        expanded = false
                    }
                )
            }
        }
    )
}

@Composable
fun NewsList(
    modifier: Modifier = Modifier,
    newsList: List<NewsDetail>,
    onItemClick: (NewsDetail) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
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
