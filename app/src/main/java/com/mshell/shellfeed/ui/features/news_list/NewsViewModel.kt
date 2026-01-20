package com.mshell.shellfeed.ui.features.news_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mshell.shellfeed.core.data.source.Resource
import com.mshell.shellfeed.core.domain.model.NewsDetail
import com.mshell.shellfeed.core.domain.repository.ShellFeedRepository
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
            repository.getTopHeadlines(country).collect {
                _newsState.value = it
            }
        }
    }
}