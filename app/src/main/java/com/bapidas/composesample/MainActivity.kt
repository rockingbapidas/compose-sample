package com.bapidas.composesample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.*
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.layout.ColumnScope.gravity
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            newsStory()
        }
    }

    @Composable
    fun newsStory() {
        Column(modifier = Modifier.padding(16.dp)) {
            imageResource()
            Spacer(modifier = Modifier.preferredHeight(16.dp))
            textLines()
            Spacer(modifier = Modifier.preferredHeight(16.dp))
            buttonCounter()
        }
    }

    @Composable
    fun imageResource() {
        val image = imageResource(R.drawable.header)
        val imageModifier = Modifier
            .preferredHeight(180.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
        Image(image, modifier = imageModifier, contentScale = ContentScale.Crop)
    }

    @Composable
    fun textLines() {
        Text(
            "A day wandering through the sandhills in Shark Fin Cove, and a few of the sights I saw",
            style = MaterialTheme.typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            "Davenport, California",
            style = MaterialTheme.typography.body2
        )
        Text(
            "December 2018",
            style = MaterialTheme.typography.body2
        )
    }

    @Composable
    fun buttonCounter() {
        val buttonModifier = Modifier.gravity(align = Alignment.CenterHorizontally)
        val count = state { 0 }
        Button(
            onClick = { count.value++ }, modifier = buttonModifier,
            backgroundColor = when {
                count.value > 30 -> Color.Red
                count.value > 10 -> Color.Green
                else -> Color.White
            }
        ) {
            Text("I've been clicked ${count.value} times")
        }
    }

    @Preview("story_preview")
    @Composable
    fun previewCompose() {
        newsStory()
    }
}