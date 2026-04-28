package com.mshell.feed.core.data.source.remote

import android.util.Log
import com.mshell.feed.core.domain.model.NewsDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import java.util.concurrent.TimeoutException

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
            } catch (e: IOException) {
                emit(ApiResponse.Error("Network error: ${e.message}"))
                Log.e("RemoteDataSource", e.toString())

            } catch (e: TimeoutException) {
                emit(ApiResponse.Error("Request timeout"))
                Log.e("RemoteDataSource", e.toString())

            } catch (e: Exception) {
                emit(ApiResponse.Error("Oops.. Something went wrong"))
                e.printStackTrace()
                Log.e("RemoteDataSource", e.toString())
            }

        }.flowOn(Dispatchers.IO)
    }
}