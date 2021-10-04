package com.bapidas.composesample.presentation.news.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.paging.PagedList
import com.bapidas.composesample.R
import com.bapidas.composesample.presentation.base.compose.NetworkImageComponent
import com.bapidas.composesample.presentation.base.theme.NewsTheme
import com.bapidas.composesample.presentation.base.theme.robotoFamily
import com.bapidas.composesample.presentation.model.Article
import com.bapidas.composesample.presentation.news.NewsViewModel

@Composable
fun NewsScreen(newsListViewModel: NewsViewModel) {
    NewsTheme {
        Scaffold(
            topBar = {
                TopBarComponent()
            },
            content = {
                NewsBodyContent(
                    newsListViewModel
                )
            })
    }
}

@Composable
private fun TopBarComponent() {
    TopAppBar(title = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.head_lines))
        }
    }, elevation = 24.dp)
}

@Composable
private fun NewsBodyContent(newsListViewModel: NewsViewModel) {
    val data by newsListViewModel.newsArticles.observeAsState()
    if (data.isNullOrEmpty().not()) {
        data?.let { pagedListArticle ->
            NewsListContent(pagedListArticle)
        }
    } else {
        NewsLoadingComponent()
    }
}

@Composable
private fun NewsListContent(news: PagedList<Article>) {
    LazyColumn {
        items(news) { article ->
            NewsCardComponent(
                article, modifier = Modifier.clickable(
                    onClick = { navigateTo(Screen.NewsDetail(article)) })
            )
        }
    }
}

@Composable
private fun NewsCardComponent(article: Article, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        val fullModifier = Modifier.fillMaxSize()
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp,
            modifier = fullModifier
        ) {
            ConstraintLayout(
                modifier = fullModifier,
                constraintSet = ConstraintSet {
                    constrain(createRefFor("imageView")) {
                        absoluteRight.linkTo(parent.absoluteRight)
                        absoluteLeft.linkTo(parent.absoluteLeft)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    constrain(createRefFor("gradientView")) {
                        absoluteRight.linkTo(parent.absoluteRight)
                        absoluteLeft.linkTo(parent.absoluteLeft)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    constrain(createRefFor("bottomView")) {
                        absoluteRight.linkTo(parent.absoluteRight)
                        absoluteLeft.linkTo(parent.absoluteLeft)
                        bottom.linkTo(parent.bottom)
                    }
                }) {
                NetworkImageComponent(
                    article.urlToImage.orEmpty(),
                    modifier = fullModifier.layoutId("imageView")
                )

                Box(
                    modifier = fullModifier
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    colorResource(R.color.colorTransparentStart),
                                    colorResource(R.color.colorTransparentEnd)
                                ),
                                startY = 0.0f,
                                endY = 350.0f
                            )
                        )
                        .layoutId("gradientView")
                )
                Column(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
                        .layoutId("bottomView")
                ) {
                    Text(
                        text = article.title.orEmpty(),
                        color = colorResource(R.color.newsTitle),
                        maxLines = 3,
                        fontSize = 20.sp,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Normal,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row {
                        Text(
                            text = article.sourceName.orEmpty(),
                            color = colorResource(R.color.newsSubTitle),
                            fontSize = 12.sp,
                            fontFamily = robotoFamily,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = article.dateString,
                            color = colorResource(R.color.newsSubTitle),
                            fontSize = 12.sp,
                            fontFamily = robotoFamily,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NewsLoadingComponent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

