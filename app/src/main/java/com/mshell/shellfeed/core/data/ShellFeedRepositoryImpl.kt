package com.mshell.shellfeed.core.data

import com.mshell.shellfeed.core.data.source.Resource
import com.mshell.shellfeed.core.data.source.remote.ApiResponse
import com.mshell.shellfeed.core.data.source.remote.RemoteDataSource
import com.mshell.shellfeed.core.domain.model.NewsDetail
import com.mshell.shellfeed.core.domain.repository.ShellFeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShellFeedRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : ShellFeedRepository {
    override fun getTopHeadlines(country: String): Flow<Resource<List<NewsDetail>?>> {
        return flow {
            remoteDataSource.getTopHeadlines(country).collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        emit(Resource.Success(apiResponse.data))
                    }
                    is ApiResponse.Error -> {
                        emit(Resource.Error(errorMessage = apiResponse.errorMessage))
                    }
                    is ApiResponse.Progress -> {
                        emit(Resource.Loading(progress = apiResponse.progress))
                    }
                    is ApiResponse.Empty -> {
                        emit(Resource.Success(null))
                    }
                }
            }
        }
    }

}