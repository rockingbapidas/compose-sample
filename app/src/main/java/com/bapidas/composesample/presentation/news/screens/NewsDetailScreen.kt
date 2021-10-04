package com.bapidas.composesample.presentation.news.screens

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.bapidas.composesample.R
import com.bapidas.composesample.presentation.base.compose.NetworkImageComponent
import com.bapidas.composesample.presentation.base.extension.getStatusBarHeight
import com.bapidas.composesample.presentation.base.theme.robotoFamily
import com.bapidas.composesample.presentation.model.Article

@Composable
fun NewsDetailsScreen(article: Article) {
    Box(modifier = Modifier.fillMaxSize()) {
        NewsDetailComponent(article)
        TopBarComponent()
    }
}

@Composable
private fun TopBarComponent() {
    val context = LocalContext.current as AppCompatActivity
    TopAppBar(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
        backgroundColor = Color(0),
        elevation = 0.dp
    ) {
        Column {
            Spacer(modifier = Modifier.height(context.getStatusBarHeight().dp))
            ConstraintLayout(modifier = Modifier.fillMaxSize(),
                constraintSet = ConstraintSet {
                    constrain(createRefFor("backArrow")) {
                        absoluteLeft.linkTo(parent.absoluteLeft)
                        top.linkTo(parent.top)
                    }
                    constrain(createRefFor("webGlobe")) {
                        absoluteRight.linkTo(parent.absoluteRight)
                        top.linkTo(parent.top)
                    }
                }) {
                Image(
                    painterResource(R.drawable.ic_arrow_back_white_42dp),
                    contentDescription = "Back Arrow",
                    modifier = Modifier
                        .layoutId("backArrow")
                        .clickable(onClick = { navigateTo(Screen.NewsList) })
                )
                Image(
                    painterResource(R.drawable.ic_baseline_language_24),
                    contentDescription = "Web Globe",
                    modifier = Modifier
                        .layoutId("webGlobe")
                        .clickable(onClick = { navigateTo(Screen.NewsList) })
                )
            }
        }
    }
}

@Composable
private fun NewsDetailComponent(article: Article) {
    val fullModifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ConstraintLayout(
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
                        endY = 1000.0f
                    )
                )
                .layoutId("gradientView")
        )
        Column(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp)
                .layoutId("bottomView")
        ) {
            Text(
                text = article.title.orEmpty(),
                color = colorResource(R.color.newsTitle),
                maxLines = 3,
                fontSize = 20.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(64.dp))
            ConstraintLayout(modifier = Modifier.fillMaxWidth(), constraintSet = ConstraintSet {
                constrain(createRefFor("sourceText")) {
                    absoluteLeft.linkTo(parent.absoluteLeft)
                    top.linkTo(parent.top)
                }
                constrain(createRefFor("dateText")) {
                    absoluteRight.linkTo(parent.absoluteRight)
                    top.linkTo(parent.top)
                }
            }) {
                Text(
                    text = article.sourceName.orEmpty(),
                    color = colorResource(R.color.newsSubTitle),
                    fontSize = 20.sp,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.layoutId("sourceText")
                )
                Text(
                    text = article.dateString,
                    color = colorResource(R.color.newsSubTitle),
                    fontSize = 20.sp,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.layoutId("dateText")
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = article.description.orEmpty(),
                color = colorResource(R.color.newsSubTitle),
                fontSize = 14.sp,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}