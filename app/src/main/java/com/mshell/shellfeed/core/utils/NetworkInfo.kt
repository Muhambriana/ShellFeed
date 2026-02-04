package com.mshell.shellfeed.core.utils

import com.mshell.shellfeed.BuildConfig

object NetworkInfo {
    val BASE_URL by lazy { BuildConfig.BASE_URL }
    val API_KEY by lazy { BuildConfig.API_KEY }
}