package com.mshell.shellfeed.core.data.source.remote

import com.mshell.shellfeed.core.domain.model.News
import com.mshell.shellfeed.core.utils.NetworkInfo
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String
    ): News
}