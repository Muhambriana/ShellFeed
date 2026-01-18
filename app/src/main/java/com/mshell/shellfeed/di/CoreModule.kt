package com.mshell.shellfeed.di

import com.mshell.shellfeed.BuildConfig
import com.mshell.shellfeed.core.utils.NetworkInfo
import com.mshell.shellfeed.core.data.source.remote.Api
import com.mshell.shellfeed.core.data.ShellFeedRepositoryImpl
import com.mshell.shellfeed.core.data.source.remote.RemoteDataSource
import com.mshell.shellfeed.core.domain.repository.ShellFeedRepository
import com.mshell.shellfeed.core.utils.ApiInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    // OkHttp Client
    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .also {
                if (BuildConfig.DEBUG) it.addInterceptor(logging)
            }
            .addInterceptor(ApiInterceptor())
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()


    }

    // Retrofit
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NetworkInfo.BASE_URL)
            .client(get())
            .build()
    }

    // API Service
    single<Api> {
        get<Retrofit>().create(Api::class.java)
    }
}

val repositoryModule = module {
    // Remote Data Source
    single { RemoteDataSource(get()) }

    // Repository
    single<ShellFeedRepository> {
        ShellFeedRepositoryImpl(get())
    }
}
