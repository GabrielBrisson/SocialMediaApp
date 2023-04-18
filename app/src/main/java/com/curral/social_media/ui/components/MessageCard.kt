package com.curral.social_media.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.curral.social_media.R
import com.curral.social_media.ui.theme.SocialMediaTheme


@Composable
fun MessageCard(
    modifier: Modifier = Modifier,
    userName: String,
    userProfilePicture: String? = null,
    content: String,
    createdAt: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(vertical = 12.dp, horizontal = 6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .padding(6.dp)
                    .size(36.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.background, CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userProfilePicture)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.profileplaceholder),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.padding(start = 6.dp),
                style = MaterialTheme.typography.titleMedium,
                text = userName,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
            Text(
                modifier = Modifier.padding(start = 6.dp).alpha(0.6f),
                text = createdAt,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = content,
            maxLines = 14,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE)
@Composable
fun PreviewMessageCard() {
    SocialMediaTheme() {
        MessageCard(
            userName = "Guilherme Brisson",
            userProfilePicture = null,
            content = "Estuda pra Chessman mano, ele é o que mais reprova",
            createdAt = "28 min"
        )
    }
}