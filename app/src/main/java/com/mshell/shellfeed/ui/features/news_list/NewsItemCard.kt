package com.mshell.shellfeed.ui.features.news_list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mshell.shellfeed.R
import com.mshell.shellfeed.core.domain.model.NewsDetail
import com.mshell.shellfeed.core.domain.model.Source
import com.mshell.shellfeed.ui.ui.theme.ShellFeedTheme

@Composable
fun NewsItemCard(news: NewsDetail) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            NewsImageCover(
                Modifier.weight(0.4f),
                news.urlToImage,
                news.title)
            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .padding(16.dp)
            ) {
                Text(
                    text = news.title ?: stringResource(R.string.hyphen),
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = news.author ?: stringResource(R.string.hyphen),
                    style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun NewsImageCover(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    newsTitle: String?
) {
    AsyncImage(
        placeholder =
            if (LocalInspectionMode.current) painterResource(R.drawable.ic_launcher_background)
            else null,
        model = imageUrl,
        contentDescription = newsTitle,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(16.dp)),
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Card with Image",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun NewsItemCardWithImagePreview() {
    ShellFeedTheme {
        NewsItemCard(
            news = NewsDetail(
                title = "Technology Breakthrough: New Discovery Changes Everything. And Do anything",
                description = "Scientists have made an incredible discovery that could revolutionize the way we live our daily lives.",
                author = "Jane Smith",
                urlToImage = "https://imagez.tmz.com/image/0d/16by9/2026/01/18/0dc0a3471f6b49fda78b4d56faa7dc92_xl.jpg",
                publishedAt = "2026-01-18T14:20:00Z",
                source = Source(name = "TechCrunch", id = "techcrunch"),
                url = "https://example.com/article2",
                content = null
            ),
        )
    }
}