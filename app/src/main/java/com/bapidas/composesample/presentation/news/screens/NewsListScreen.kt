package com.bapidas.composesample.presentation.news.screens

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.remember
import androidx.paging.PagedList
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.tag
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.VerticalGradient
import androidx.ui.layout.*
import androidx.ui.livedata.observeAsState
import androidx.ui.material.*
import androidx.ui.res.colorResource
import androidx.ui.res.stringResource
import androidx.ui.text.font.font
import androidx.ui.text.font.fontFamily
import androidx.ui.text.style.TextOverflow
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import androidx.ui.viewinterop.AndroidView
import com.bapidas.composesample.R
import com.bapidas.composesample.presentation.base.compose.NetworkImageComponent
import com.bapidas.composesample.presentation.base.theme.NewsTheme
import com.bapidas.composesample.presentation.model.Article
import com.bapidas.composesample.presentation.news.NewsListViewModel

@Composable
fun NewsScreen(newsListViewModel: NewsListViewModel) {
    NewsTheme {
        Scaffold(
            scaffoldState = remember {
                ScaffoldState()
            },
            topAppBar = {
                TopBarComponent()
            },
            bodyContent = {
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
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalGravity = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.head_lines))
        }
    }, elevation = 24.dp)
}

@Composable
private fun NewsBodyContent(newsListViewModel: NewsListViewModel) {
    val data by newsListViewModel.newsArticles.observeAsState()
    if (data.isNullOrEmpty().not()) {
        data?.let {
            NewsListConent(
                it
            )
        }
    } else {
        NewsLoadingComponent()
    }
}

@Composable
private fun NewsListConent(news: PagedList<Article>) {
    /*VerticalScroller {
        news.forEach {
            NewsCardComponent(
                it,
                modifier = Modifier.clickable(onClick = {
                    navigateTo(
                        Screen.NewsDetail(
                            it
                        )
                    )
                })
            )
        }
    }*/

    AdapterList(news) {
        NewsCardComponent(
            it,
            modifier = Modifier.clickable(onClick = {
                navigateTo(
                    Screen.NewsDetail(
                        it
                    )
                )
            })
        )
    }
}

@Composable
private fun NewsCardComponent(article: Article, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
            .preferredHeight(200.dp)
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
                    tag("imageView").apply {
                        right constrainTo parent.right
                        left constrainTo parent.left
                        top constrainTo parent.top
                        bottom constrainTo parent.bottom
                    }
                    tag("gradientView").apply {
                        right constrainTo parent.right
                        left constrainTo parent.left
                        top constrainTo parent.top
                        bottom constrainTo parent.bottom
                    }
                    tag("bottomView").apply {
                        right constrainTo parent.right
                        left constrainTo parent.left
                        bottom constrainTo parent.bottom
                    }
                }) {
                NetworkImageComponent(
                    article.urlToImage.orEmpty(),
                    modifier = fullModifier.tag("imageView")
                )

                Box(
                    modifier = fullModifier.drawBackground(
                        VerticalGradient(
                            listOf(
                                colorResource(R.color.colorTransparentStart),
                                colorResource(R.color.colorTransparentEnd)
                            ),
                            startY = 0.0f,
                            endY = 350.0f
                        )
                    ).tag("gradientView")
                )
                Column(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
                        .tag("bottomView")
                ) {
                    Text(
                        text = article.title.orEmpty(),
                        color = colorResource(R.color.newsTitle),
                        maxLines = 3,
                        fontSize = 20.sp,
                        fontFamily = fontFamily(font(R.font.roboto_slab_regular)),
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.preferredHeight(24.dp))
                    Row {
                        Text(
                            text = article.sourceName.orEmpty(),
                            color = colorResource(R.color.newsSubTitle),
                            fontSize = 12.sp,
                            fontFamily = fontFamily(font(R.font.roboto_slab_bold))
                        )
                        Spacer(modifier = Modifier.preferredWidth(16.dp))
                        Text(
                            text = article.dateString,
                            color = colorResource(R.color.newsSubTitle),
                            fontSize = 12.sp,
                            fontFamily = fontFamily(font(R.font.roboto_slab_regular))
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NewsLoadingComponent() {
    Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
        CircularProgressIndicator()
    }
}

