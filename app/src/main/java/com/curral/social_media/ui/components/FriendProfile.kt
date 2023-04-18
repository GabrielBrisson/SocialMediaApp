package com.curral.social_media.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
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
    Column(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(friendProfilePicture)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.profileplaceholder),
            contentDescription = "",
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(top = 4.dp),
            color = MaterialTheme.colorScheme.onBackground,
            text = friendName,
            maxLines = 2,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewFriendProfile() {
    SocialMediaTheme() {
        FriendProfile(friendName = "Benjamin Arola", friendProfilePicture = null)
    }
}
