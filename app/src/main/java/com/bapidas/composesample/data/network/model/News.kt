package com.bapidas.composesample.data.network.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("source")
    val source: Source? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("urlToImage")
    val urlToImage: String? = null
)