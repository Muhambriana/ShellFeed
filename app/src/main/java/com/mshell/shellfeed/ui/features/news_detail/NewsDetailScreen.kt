package com.mshell.shellfeed.ui.features.news_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mshell.shellfeed.core.domain.model.NewsDetail
import com.mshell.shellfeed.core.domain.model.Source
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(
    newsDetail: NewsDetail,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit
) {
    Scaffold(
        topBar = {
            // Transparent top bar with back and share buttons
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 48.dp)
            ) {
                // Back button
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(48.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Share button
                IconButton(
                    onClick = onShareClick,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(48.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            // Hero Image
            newsDetail.urlToImage?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = newsDetail.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Content Container
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                // Title
                Text(
                    text = newsDetail.title ?: "No Title",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        lineHeight = 32.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Author and Date Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Author with avatar placeholder
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Avatar placeholder
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = (newsDetail.author?.firstOrNull()?.uppercase() ?: "A"),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = newsDetail.author ?: "Unknown Author",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    // Published Date
                    Text(

                        text = formatDate(newsDetail.publishedAt),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Description
                newsDetail.description?.let { description ->
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            lineHeight = 28.sp,
                            fontSize = 16.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Justify
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Content
                newsDetail.content?.let { content ->
                    Text(
                        text = content,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            lineHeight = 28.sp,
                            fontSize = 16.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Justify
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Source
                newsDetail.source?.name?.let { sourceName ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Source: $sourceName",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

private fun formatDate(dateString: String?): String {
    if (dateString == null) return ""

    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = inputFormat.parse(dateString)

        val outputFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
        date?.let { outputFormat.format(it) } ?: ""
    } catch (_: Exception) {
        ""
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NewsDetailScreenPreview() {
    MaterialTheme {
        NewsDetailScreen(
            newsDetail = NewsDetail(
                title = "Contact Lost With Sriwijaya Air Boeing 737-500 After Take Off",
                author = "John Smith",
                description = "An Indonesian passenger plane carrying 62 people lost contact with air traffic controllers shortly after takeoff from the nation's capital of Jakarta on Saturday, according to state transportation officials.",
                content = "The Ministry of Transportation confirmed that airport authorities lost contact with the plane, Sriwijaya Air Flight 182, at approximately 2:40 p.m. local time. The plane, a Boeing 737-500, had taken off from Jakarta less than an hour earlier bound for the city of Pontianak on Indonesia's Borneo island. Typically, it's a flight that is around 90 minutes long.",
                urlToImage = "https://via.placeholder.com/800x400",
                publishedAt = "2020-01-10T14:40:00Z",
                source = Source(name = "CNN", id = "cnn"),
                url = "https://example.com/article"
            ),
            onBackClick = {},
            onShareClick = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "No Image")
@Composable
fun NewsDetailScreenNoImagePreview() {
    MaterialTheme {
        NewsDetailScreen(
            newsDetail = NewsDetail(
                title = "Breaking News: Important Event Happening Now",
                author = "Jane Doe",
                description = "This is a sample article description that provides context about the news story.",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.",
                urlToImage = null,
                publishedAt = "2026-01-18T10:30:00Z",
                source = Source(name = "BBC News", id = "bbc-news"),
                url = "https://example.com/article2"
            ),
            onBackClick = {},
            onShareClick = {}
        )
    }
}

