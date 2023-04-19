package com.curral.social_media.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.curral.social_media.R
import com.curral.social_media.ui.theme.SocialMediaTheme

@Composable
fun ImageProfile(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    shape: Shape = CircleShape,
    borderStroke: BorderStroke = BorderStroke(width = 4.dp, color = Color.White),
    onClick: (() -> Unit)? = null,
) {
    Box(modifier = modifier) {
        if (imageUrl.isNullOrEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape)
                    .border(borderStroke, shape)
                    .clickable { onClick?.let { it() } },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profileplaceholder),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        } else {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape)
                    .border(borderStroke, shape)
                    .clickable { onClick?.let { it() } },
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.profileplaceholder),
                contentDescription = null
            )
        }

    }
}

@Preview
@Composable
fun PreviewImageProfile() {
    SocialMediaTheme {
        ImageProfile(modifier = Modifier.size(100.dp), imageUrl = null)
    }
}