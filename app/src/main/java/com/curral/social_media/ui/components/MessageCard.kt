package com.curral.social_media.ui.components

import android.text.format.DateUtils
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.curral.social_media.ui.theme.SocialMediaTheme
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MessageCard(
    modifier: Modifier = Modifier,
    userName: String,
    userProfilePicture: String? = null,
    content: String,
    createdAt: String
) {
    val relativeTimeSpan = getRelativeTimeSpan(createdAt)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(vertical = 12.dp, horizontal = 6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ImageProfile(
                modifier = Modifier
                    .padding(6.dp)
                    .size(36.dp),
                imageUrl = userProfilePicture,
                borderStroke = BorderStroke(2.dp, MaterialTheme.colorScheme.background)
            )
            Text(
                modifier = Modifier.padding(start = 6.dp),
                style = MaterialTheme.typography.titleMedium,
                text = userName,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1
            )
            Text(
                modifier = Modifier
                    .padding(start = 6.dp)
                    .alpha(0.6f),
                text = relativeTimeSpan ?: "",
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

private fun getRelativeTimeSpan(dateString: String): String? {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS", Locale("pt", "br"))
    val due =dateFormat.parse(dateString)?.time
    val now = System.currentTimeMillis()
    return if (due != null) {
        DateUtils.getRelativeTimeSpanString(
            due, now, 0L, DateUtils.FORMAT_ABBREV_ALL
        ).toString()
    } else null
}

@Preview(wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE)
@Composable
fun PreviewMessageCard() {
    SocialMediaTheme {
        MessageCard(
            userName = "Guilherme Brisson",
            userProfilePicture = null,
            content = "Estuda pra Chessman mano, ele é o que mais reprova",
            createdAt = "2023-04-18T02:11:58.21"
        )
    }
}