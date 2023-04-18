package com.curral.social_media.presentation.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.curral.social_media.R
import com.curral.social_media.domain.model.User

@Composable
fun FilteredUser(
    modifier: Modifier = Modifier,
    user: User,
    onAdd: (id: String) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { onAdd(user.id) }) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
        }

        if (!user.profilePicture.isNullOrEmpty()) {
            AsyncImage(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(40.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.profilePicture)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.profileplaceholder),
                contentDescription = "",
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "id: ${user.id}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(text = user.name, style = MaterialTheme.typography.titleMedium)
        }


    }
}