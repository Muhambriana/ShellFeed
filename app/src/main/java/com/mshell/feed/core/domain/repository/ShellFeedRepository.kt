package com.mshell.feed.core.domain.repository

import com.mshell.feed.core.data.source.Resource
import com.mshell.feed.core.domain.model.NewsDetail
import kotlinx.coroutines.flow.Flow

interface ShellFeedRepository {
    fun getTopHeadlines(country: String): Flow<Resource<List<NewsDetail>?>>
}