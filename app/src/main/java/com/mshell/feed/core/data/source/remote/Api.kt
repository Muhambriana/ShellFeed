package com.mshell.feed.core.data.source.remote

import com.mshell.feed.core.domain.model.News
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String
    ): News
}