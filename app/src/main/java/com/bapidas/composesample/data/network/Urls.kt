package com.bapidas.composesample.data.network

import com.bapidas.composesample.BuildConfig


object Urls {
    var BASE_URL = BuildConfig.API_DOMAIN

    object News {
        const val ARTICLES_API = "/v2/everything"
    }
}