package com.curral.social_media.presentation.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.curral.social_media.R
import com.curral.social_media.ui.theme.SocialMediaTheme

@Composable
fun FriendProfile(
    modifier: Modifier = Modifier,
    friendName: String,
    friendProfilePicture: String?
) {
    Column(modifier = modifier) {
        AsyncImage(
            modifier = Modifier.clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(friendProfilePicture)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.profileplaceholder),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = friendName,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MessageCard(
    modifier: Modifier = Modifier,
    friendName: String,
    friendProfilePicture: String?,
    message: String
) {
    Column() {
        Row {
            AsyncImage(
                modifier = Modifier.clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(friendProfilePicture)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.profileplaceholder),
                contentDescription = ""
            )
            Text(modifier = Modifier.padding(start = 6.dp), text = friendName)
        }
        Text(
            modifier = Modifier.padding(6.dp),
            text = message,
            maxLines = 14,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewFriendProfile() {
    SocialMediaTheme() {
        FriendProfile(friendName = "Francisco", friendProfilePicture = null)
        MessageCard(
            friendName = "Francisco",
            friendProfilePicture = "",
            message = "aff n consigo ler tá em rico"
        )
    }
}