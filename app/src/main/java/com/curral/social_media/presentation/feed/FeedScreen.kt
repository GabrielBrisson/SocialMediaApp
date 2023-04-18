package com.curral.social_media.presentation.feed

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.curral.social_media.domain.model.Post
import com.curral.social_media.domain.model.User
import com.curral.social_media.ui.theme.SocialMediaTheme

@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = hiltViewModel(),
    onProfile: (id: String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    FeedScreen(
        modifier = modifier,
        uiState = uiState,
        onProfile = onProfile
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FeedScreen(
    modifier: Modifier = Modifier,
    uiState: FeedUiState,
    onProfile: (id: String) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "Explore",
                    fontWeight = FontWeight.SemiBold,
                )
            }, actions = {
                Icon(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .border(
                            1.dp,
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = CircleShape
                        )
                        .padding(6.dp)
                        .clickable { uiState.currentUser?.id?.let { onProfile(it) } },
                    imageVector = Icons.Rounded.Person,
                    contentDescription = ""
                )
            }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Button")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = modifier.padding(paddingValues)) {
            if (uiState.loading) {
                item { LinearProgressIndicator(modifier = Modifier.fillMaxWidth()) }
            }
            uiState.friends?.let { friends ->
                if (uiState.friends.isEmpty()) {
                    item {
                        Column {
                            Text(text = "Está tudo muito calmo por aqui")
                        }
                    }
                }
                item {
                    LazyRow(modifier = Modifier.padding(top = 40.dp)) {
                        items(friends) { friend ->
                            FriendProfile(
                                friendName = friend.name,
                                friendProfilePicture = friend.profilePicture
                            )
                        }
                    }
                }
            }
            uiState.feed?.let { posts ->
                items(posts) { post ->
                    MessageCard(
                        userName = post.author ?: "",
                        content = post.content,
                        createdAt = post.createdAt
                    )
                }
            }
        }
    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewFeedScreen() {
    SocialMediaTheme {
        val friends = listOf(
            User(id = "1", name = "Guigui Guelerme", profilePicture = ""),
            User(id = "2", name = "Gabriel", profilePicture = ""),
            User(id = "3", name = "Felipe", profilePicture = ""),
            User(id = "4", name = "Davi", profilePicture = ""),
            User(id = "5", name = "Lucas", profilePicture = "")
        )
        val posts = listOf(
            Post(
                author = friends[2].name,
                content = "Eu tinha feito muito isso pq eu dei uma consertada, mas tinha um q tava inglês, outro q tava em portguês tava uma loucura!",
                createdAt = "25 min ago"
            ),
            Post(
                author = friends[1].name,
                content = "lmao lol ela é tão doidinhaaaa, such a craze😝, ela botou um cone na cabeça hahahahaha",
                createdAt = "2 hours ago"
            ),
            Post(
                author = friends[0].name,
                content = "Dar enter na última linha é uma boa prática!",
                createdAt = "1 min ago"
            ),
            Post(
                author = friends[2].name,
                content = "Eu tinha feito muito isso pq eu dei uma consertada, mas tinha um q tava inglês, outro q tava em portguês tava uma loucura!",
                createdAt = "25 min ago"
            ),
            Post(
                author = friends[1].name,
                content = "lmao lol ela é tão doidinhaaaa, such a craze, ela botou um cone na cabeça hahahahaha",
                createdAt = "2 hours ago"
            ),
            Post(
                author = friends[0].name,
                content = "Dar enter na última linha é uma boa prática!",
                createdAt = "1 min ago"
            )
        )
        FeedScreen(
            uiState = FeedUiState(loading = true, friends = friends, feed = posts),
            onProfile = { }
        )
    }
}