package com.bapidas.composesample.presentation.news.screens

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import androidx.ui.animation.Crossfade
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.bapidas.composesample.presentation.model.Article
import com.bapidas.composesample.presentation.news.NewsListViewModel

@Composable
fun AppContent(
    newsListViewModel: NewsListViewModel
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