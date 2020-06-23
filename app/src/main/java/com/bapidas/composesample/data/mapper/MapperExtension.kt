package com.bapidas.composesample.data.mapper

import com.bapidas.composesample.data.db.model.ArticleEntity
import com.bapidas.composesample.data.network.model.News
import com.bapidas.composesample.presentation.model.Article

fun ArticleEntity.toArticle(): Article {
    return Article(
        publishedAt,
        title,
        description,
        urlToImage,
        url,
        sourceName
    )
}

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        publishedAt,
        title,
        description,
        urlToImage,
        url,
        sourceName
    )
}

fun News.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        publishedAt,
        title,
        description,
        urlToImage,
        url,
        source?.name
    )
}

fun News.toArticle(): Article {
    return Article(
        publishedAt,
        title,
        description,
        urlToImage,
        url,
        source?.name
    )
}