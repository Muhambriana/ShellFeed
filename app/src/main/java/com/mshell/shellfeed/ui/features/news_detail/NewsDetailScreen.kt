package com.mshell.shellfeed.ui.features.news_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mshell.shellfeed.R
import com.mshell.shellfeed.core.domain.model.NewsDetail
import com.mshell.shellfeed.core.domain.model.Source
import com.mshell.shellfeed.ui.ui.theme.LoraFontFamily
import com.mshell.shellfeed.ui.ui.theme.ShellFeedTheme
import com.mshell.shellfeed.utils.TimeUtil

private val HEADER_HEIGHT = 470.dp
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
        NewsHeader(newsDetail, headerHeight = HEADER_HEIGHT)

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehaviors.nestedScrollConnection),
            containerColor = Color.Transparent,
            topBar = {
                NewsDetailTopBar(
                    scrollBehavior = scrollBehaviors,
                    onBackClick = onBackClick,
                    onShareClick = onShareClick
                )
            },
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    top = HEADER_HEIGHT,
                    bottom = paddingValues.calculateBottomPadding() + 24.dp,
                    start = 24.dp,
                    end = 24.dp
                )
            ) {
                item {
                    NewsBodyContent(newsDetail = newsDetail)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {},
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        actions = {
            IconButton(
                onClick = onShareClick
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = MaterialTheme.colorScheme.surface,
        ),
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun NewsHeader(newsDetail: NewsDetail, headerHeight: androidx.compose.ui.unit.Dp) {
    val shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(headerHeight)
            .clip(shape)
    ) {
        AsyncImage(
            placeholder = ColorPainter(Color.LightGray),
            model = ImageRequest.Builder(LocalContext.current)
                .data(newsDetail.urlToImage)
                .crossfade(true)
                .build(),
            contentDescription = "News Header Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                        startY = 300f
                    )
                )
        )
        
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Text(
                text = newsDetail.title ?: stringResource(R.string.hyphen),
                style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
                color = Color.White,
            )

            Spacer(modifier = Modifier.height(8.dp))

            NewsMetaRow(newsDetail = newsDetail)
        }
    }
}

@Composable
fun NewsMetaRow(newsDetail: NewsDetail) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        val textStyle = MaterialTheme.typography.bodySmall.copy(
            color = Color.White.copy(alpha = 0.8f)
        )

        Row {
            Text(
                text = "By ",
                style = textStyle,
            )
            Text(
                text = newsDetail.author ?: "Unknown",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Green.copy(alpha = 0.8f),
                )
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            style = textStyle,
            text = "Published ${TimeUtil.getTimeAgo(newsDetail.publishedAt)}",
        )
    }
}

@Composable
fun NewsBodyContent(newsDetail: NewsDetail) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier.padding(top = 10.dp)
    ) {
        Text(
            text = newsDetail.content ?: stringResource(R.string.hyphen),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = LoraFontFamily
            ),
            lineHeight = 26.sp
        )
        ExtendedFloatingActionButton(
            onClick = { newsDetail.url?.let { uriHandler.openUri(it) } },
            shape = RoundedCornerShape(20.dp),
            containerColor = Color(0xFF3E7EE8),
            contentColor = Color.White,
            modifier = Modifier.padding(top = 20.dp)
                .width(150.dp)
                .size(40.dp)
        ) {
            Icon(
                painter =  painterResource(R.drawable.ic_link),
                contentDescription = stringResource(R.string.read_more),
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.read_more),
                fontSize = 15.sp
            )
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
                publishedAt = "2026-01-26T12:20:00Z",
                source = Source(name = "TechCrunch", id = "techcrunch"),
                url = "https://example.com/article2",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            ),
            onBackClick = {},
            onShareClick = {},
        )
    }
}