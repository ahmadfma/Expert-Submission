package com.app.core.di

import androidx.room.Room
import com.app.core.data.repository.ArticleRepository
import com.app.core.data.source.local.LocalDataSource
import com.app.core.data.source.local.room.ArticleDatabase
import com.app.core.data.source.remote.RemoteDataSource
import com.app.core.data.source.remote.network.ApiService
import com.app.core.domain.repository.IArticleRepository
import com.app.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<ArticleDatabase>().articleDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            ArticleDatabase::class.java, "Article.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IArticleRepository> {
        ArticleRepository(
            get(),
            get(),
            get()
        )
    }
}