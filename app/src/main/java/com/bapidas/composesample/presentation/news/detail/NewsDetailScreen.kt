package com.bapidas.composesample.presentation.news.detail

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.core.tag
import androidx.ui.foundation.*
import androidx.ui.graphics.Color
import androidx.ui.graphics.VerticalGradient
import androidx.ui.layout.*
import androidx.ui.livedata.observeAsState
import androidx.ui.material.TopAppBar
import androidx.ui.res.colorResource
import androidx.ui.res.vectorResource
import androidx.ui.text.font.font
import androidx.ui.text.font.fontFamily
import androidx.ui.text.style.TextOverflow
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.bapidas.composesample.R
import com.bapidas.composesample.presentation.base.extension.getStatusBarHeight
import com.bapidas.composesample.presentation.base.networkImageComponent
import com.bapidas.composesample.presentation.model.Article

@Composable
fun NewsDetailScreen(newsDetailViewModel: NewsDetailViewModel) {
    Stack(modifier = Modifier.fillMaxSize()) {
        NewsDetail(newsDetailViewModel)
        TopBar()
    }
}

@Composable
private fun TopBar() {
    val context = ContextAmbient.current as AppCompatActivity
    TopAppBar(
        modifier = Modifier.preferredHeight(100.dp).fillMaxWidth(),
        backgroundColor = Color(0),
        elevation = 0.dp
    ) {
        Column {
            Spacer(modifier = Modifier.preferredHeight(context.getStatusBarHeight().dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    vectorResource(R.drawable.ic_arrow_back_white_42dp),
                    modifier = Modifier.clickable(onClick = {
                        context.onBackPressed()
                    })
                )
                Image(
                    vectorResource(R.drawable.ic_baseline_language_24),
                    modifier = Modifier.clickable(onClick = {

                    })
                )
            }
        }
    }
}

@Composable
private fun NewsDetail(newsDetailViewModel: NewsDetailViewModel) {
    val article by newsDetailViewModel.article.observeAsState()
    article?.let { NewsDetailContent(it) }
}

@Composable
private fun NewsDetailContent(article: Article) {
    val fullModifier = Modifier.fillMaxWidth().fillMaxHeight()
    ConstraintLayout(
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
        networkImageComponent(
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
                    endY = 1000.0f
                )
            ).tag("gradientView")
        )
        Column(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp).tag("bottomView")
        ) {
            Text(
                text = article.title.orEmpty(),
                color = colorResource(R.color.newsTitle),
                maxLines = 3,
                fontSize = 20.sp,
                fontFamily = fontFamily(font(R.font.roboto_slab_bold)),
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.preferredHeight(64.dp))
            Row {
                Text(
                    text = article.sourceName.orEmpty(),
                    color = colorResource(R.color.newsSubTitle),
                    fontSize = 20.sp,
                    fontFamily = fontFamily(font(R.font.roboto_slab_regular))
                )
                Text(
                    text = article.dateString,
                    color = colorResource(R.color.newsSubTitle),
                    fontSize = 20.sp,
                    fontFamily = fontFamily(font(R.font.roboto_slab_regular))
                )
            }
            Spacer(modifier = Modifier.preferredHeight(16.dp))
            Text(
                text = article.description.orEmpty(),
                color = colorResource(R.color.newsSubTitle),
                fontSize = 14.sp,
                fontFamily = fontFamily(font(R.font.roboto_slab_regular)),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}