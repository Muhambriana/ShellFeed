package com.mshell.shellfeed.ui.di

import com.mshell.shellfeed.ui.features.news_list.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewsViewModel(get()) }
}