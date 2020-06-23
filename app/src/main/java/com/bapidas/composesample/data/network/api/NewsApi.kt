package com.bapidas.composesample.data.network.api

import com.bapidas.composesample.data.network.Urls
import com.bapidas.composesample.data.network.response.NewsListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(Urls.News.ARTICLES_API)
    suspend fun getNewsArticles(
        @Query("q") category: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String
    ): NewsListResponse
}