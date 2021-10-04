package com.bapidas.composesample.presentation.base.compose

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun NetworkImageComponent(
    urlToImage: String,
    modifier: Modifier = Modifier
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(urlToImage)
        .build()

    val shimmerParams = ShimmerParams(
        baseColor = MaterialTheme.colors.background,
        highlightColor = MaterialTheme.colors.secondary,
        durationMillis = 350,
        dropOff = 0.65f,
        tilt = 20f
    )

    CoilImage(
        imageRequest = imageRequest,
        modifier = modifier,
        shimmerParams = shimmerParams
    )
}