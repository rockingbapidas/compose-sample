package com.bapidas.composesample.presentation.base.compose

import androidx.compose.*
import androidx.core.graphics.drawable.toBitmap
import androidx.ui.core.ContentScale
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Image
import androidx.ui.graphics.ImageAsset
import androidx.ui.graphics.asImageAsset
import androidx.ui.layout.fillMaxHeight
import androidx.ui.layout.fillMaxWidth
import coil.Coil
import coil.request.GetRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun NetworkImageComponent(
    urlToImage: String,
    modifier: Modifier = Modifier.fillMaxHeight().fillMaxWidth()
) {

    var image by state<ImageAsset?> { null }
    val context = ContextAmbient.current

    onCommit(urlToImage) {
        val job = CoroutineScope(Dispatchers.Main.immediate).launch {
            val request = GetRequest.Builder(context)
                .data(urlToImage)
                .build()
            image = Coil.imageLoader(context).execute(request).drawable?.toBitmap()?.asImageAsset()
        }

        onDispose {
            image = null
            job.cancel()
        }
    }

    val theImage = image
    if (theImage != null) {
        Image(asset = theImage, modifier = modifier, contentScale = ContentScale.Crop)
    }
}