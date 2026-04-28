package com.mshell.feed.core.utils

import com.mshell.feed.BuildConfig

object NetworkInfo {
    val BASE_URL by lazy { BuildConfig.BASE_URL }
    val API_KEY by lazy { BuildConfig.API_KEY }
}