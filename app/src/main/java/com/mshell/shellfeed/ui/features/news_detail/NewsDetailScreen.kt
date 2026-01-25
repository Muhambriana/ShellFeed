package com.mshell.shellfeed.ui.features.news_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mshell.shellfeed.R
import com.mshell.shellfeed.core.domain.model.NewsDetail
import com.mshell.shellfeed.core.domain.model.Source
import com.mshell.shellfeed.ui.ui.theme.ShellFeedTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(
    newsDetail: NewsDetail,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
) {
    val scrollBehaviors = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            placeholder =
                if (LocalInspectionMode.current) painterResource(R.drawable.ic_launcher_background)
                else null,
            model = ImageRequest.Builder(LocalContext.current)
                .data(newsDetail.url)
                .crossfade(true)
                .build(),
            contentDescription = "News Header Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 40.dp,
                        bottomEnd = 40.dp
                    )
                )
        )

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehaviors.nestedScrollConnection),
            containerColor = Color.Transparent,
            topBar = {
                CenterAlignedTopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(
                            onClick = onBackClick
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = onShareClick
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color.Transparent,
                        scrolledContainerColor = MaterialTheme.colorScheme.surface,
                    ),
                    scrollBehavior = scrollBehaviors
                )
            },
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
                contentPadding = PaddingValues(
                    top = 380.dp,
                    bottom = paddingValues.calculateBottomPadding(),
                )
            ) {
                item {

                }
                item {
                    Text(
                        text = newsDetail.title ?: stringResource(R.string.hyphen),
                        style = MaterialTheme.typography.bodyLarge,
                        lineHeight = 24.sp // Good for readability in news apps
                    )
                }
            }
        }
    }
}

@Composable
@Preview(
    showSystemUi = true
)
fun NewsDetailScreenPreview() {
    ShellFeedTheme() {
        NewsDetailScreen(
            newsDetail = NewsDetail(
                title = "Technology Breakthrough: New Discovery Changes Everything. And Do anything",
                description = "Scientists have made an incredible discovery that could revolutionize the way we live our daily lives.",
                author = "Jane Smith",
                urlToImage = "https://imagez.tmz.com/image/0d/16by9/2026/01/18/0dc0a3471f6b49fda78b4d56faa7dc92_xl.jpg",
                publishedAt = "2026-01-18T14:20:00Z",
                source = Source(name = "TechCrunch", id = "techcrunch"),
                url = "https://example.com/article2",
                content = null
            ),
            onBackClick = {},
            onShareClick = {},
        )
    }
}