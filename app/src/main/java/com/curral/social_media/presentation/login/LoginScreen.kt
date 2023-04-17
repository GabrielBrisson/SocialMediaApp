package com.curral.social_media.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.curral.social_media.R

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val loginGradient = listOf<Color>(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.tertiary
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(loginGradient)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            modifier = Modifier.padding(30.dp),
            text = "LOG IN",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            letterSpacing = 8.sp
        )
        AsyncImage(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(4.dp, Color.White, CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data("")
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.profileplaceholder),
            contentDescription = ""
        )
        LoginTextField(modifier = Modifier.padding(top = 26.dp), label = "Username")
        Button(
            modifier = Modifier.width(310.dp).padding(12.dp),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text(
                text = "Enter",
                modifier = Modifier
                    .graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        val brush =
                            Brush.horizontalGradient(listOf(Color(0xFF983493), Color(0xFF815345)))
                        onDrawWithContent {
                            drawContent()
                            drawRect(brush, blendMode = BlendMode.SrcAtop)
                        }
                    }
            )
        }
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    MaterialTheme {
        LoginScreen()
    }
}