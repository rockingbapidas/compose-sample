package com.bapidas.composesample.data.network.response

import com.bapidas.composesample.data.network.model.News
import com.google.gson.annotations.SerializedName

data class NewsListResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<News>
)