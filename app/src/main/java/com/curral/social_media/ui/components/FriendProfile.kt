package com.curral.social_media.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        ImageProfile(
            modifier = Modifier.size(70.dp),
            imageUrl = friendProfilePicture,
            borderStroke = BorderStroke(3.dp, MaterialTheme.colorScheme.primary)
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
