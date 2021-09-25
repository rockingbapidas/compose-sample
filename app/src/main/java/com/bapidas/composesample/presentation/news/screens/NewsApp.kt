package com.bapidas.composesample.presentation.news.screens

import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bapidas.composesample.presentation.model.Article
import com.bapidas.composesample.presentation.news.NewsViewModel

@Composable
fun AppContent(
    newsListViewModel: NewsViewModel
) {
    Crossfade(NewsStatus.currentScreen) { screen ->
        Surface(color = MaterialTheme.colors.background) {
            when (screen) {
                is Screen.NewsList -> NewsScreen(
                    newsListViewModel = newsListViewModel
                )
                is Screen.NewsDetail -> NewsDetailsScreen(
                    screen.article
                )
                is Screen.NewsBrowser -> NewsBrowserScreen(screen.address)
            }
        }
    }
}

sealed class Screen {
    object NewsList : Screen()
    data class NewsDetail(val article: Article) : Screen()
    data class NewsBrowser(val address: String) : Screen()
}

object NewsStatus {
    var currentScreen by mutableStateOf<Screen>(
        Screen.NewsList
    )
}

fun navigateTo(destination: Screen) {
    NewsStatus.currentScreen = destination
}