package com.mshell.shellfeed.core.domain.repository

import com.mshell.shellfeed.core.data.source.Resource
import com.mshell.shellfeed.core.domain.model.NewsDetail
import kotlinx.coroutines.flow.Flow

interface ShellFeedRepository {
    fun getTopHeadlines(country: String): Flow<Resource<List<NewsDetail>?>>
}