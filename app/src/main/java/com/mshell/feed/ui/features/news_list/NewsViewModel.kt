package com.mshell.feed.ui.features.news_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mshell.feed.core.data.source.Resource
import com.mshell.feed.core.domain.model.NewsDetail
import com.mshell.feed.core.domain.repository.ShellFeedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: ShellFeedRepository): ViewModel() {
    private val _newsState = MutableStateFlow<Resource<List<NewsDetail>?>>(Resource.Loading())
    val newsState: StateFlow<Resource<List<NewsDetail>?>> =_newsState.asStateFlow()

    init {
        getTopHeadLines("us")
    }

    fun getTopHeadLines(country: String) {
        viewModelScope.launch {
            repository.getTopHeadlines(country).collect { resource ->
                when(resource) {
                    is Resource.Success -> {
                        val filteredList = resource.data?.filter {
                            it.author.isNullOrBlank().not() && it.urlToImage.isNullOrBlank().not()
                        }
                        _newsState.value = Resource.Success(filteredList)
                    }
                    else -> {
                        _newsState.value = resource
                    }
                }
            }
        }
    }
}