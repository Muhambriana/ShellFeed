package com.mshell.shellfeed.core.data.source.remote

import android.util.Log
import com.mshell.shellfeed.core.domain.model.NewsDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val api: Api) {
    fun getTopHeadlines(country: String): Flow<ApiResponse<List<NewsDetail>?>> {
        return flow {
            try {
                val response = api.getTopHeadlines(country = country)
                if (response.status != "ok") {
                    emit(ApiResponse.Error("Failed to fetch data"))
                    return@flow
                }

                val data = response.articles
                emit(
                    ApiResponse.Success(data = data)
                )
            } catch (e: Exception) {
                emit(ApiResponse.Error("Oops.. Something went wrong"))
                e.printStackTrace()
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}