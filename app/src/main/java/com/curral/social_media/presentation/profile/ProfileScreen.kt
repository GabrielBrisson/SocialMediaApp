package com.curral.social_media.presentation.profile


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.curral.social_media.domain.model.Post
import com.curral.social_media.domain.model.User
import com.curral.social_media.ui.components.FriendProfile
import com.curral.social_media.ui.components.ImageProfile
import com.curral.social_media.ui.components.MessageCard
import com.curral.social_media.ui.theme.SocialMediaTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    onAddFriend: () -> Unit,
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    ProfileScreen(
        modifier = modifier,
        uiState = uiState,
        onAddFriend = onAddFriend,
        onBack = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProfileScreen(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    onAddFriend: () -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Profile",
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "")
                    }
                }
            )
        },
    ) { paddingValues ->
        uiState.user?.let { user ->
            LazyColumn(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxWidth(),
            ) {
                // profile header
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp, top = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ImageProfile(
                            modifier = Modifier.size(100.dp),
                            imageUrl = user.profilePicture,
                        )
                        Text(
                            modifier = Modifier
                                .width(200.dp)
                                .padding(top = 10.dp),
                            text = user.name,
                            style = MaterialTheme.typography.headlineSmall,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                        )
                    }
                }

                // my friends
                item {
                    Text(
                        modifier = Modifier.padding(start = 15.dp, top = 30.dp, bottom = 10.dp),
                        text = "My friends",
                        style = MaterialTheme.typography.titleLarge
                    )
                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        item {
                            Column(
                                modifier = modifier
                                    .width(IntrinsicSize.Min)
                                    .padding(start = 15.dp, end = 12.dp)
                                    .clickable { onAddFriend() },
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(CircleShape)
                                        .border(3.dp, Color.LightGray, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                                }
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(60.dp)
                                        .padding(top = 4.dp),
                                    color = MaterialTheme.colorScheme.onBackground,
                                    text = "Add friend",
                                    maxLines = 2,
                                    fontWeight = FontWeight.SemiBold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        user.friends?.let { friends ->
                            items(friends) { friend ->
                                FriendProfile(
                                    friendName = friend.name,
                                    friendProfilePicture = friend.profilePicture
                                )
                            }
                        }
                    }
                }

                // my posts
                user.posts?.let { posts ->
                    item {
                        Text(
                            modifier = Modifier.padding(start = 15.dp, top = 30.dp, bottom = 10.dp),
                            text = "My posts",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    items(posts) { post ->
                        MessageCard(
                            userName = post.author ?: user.name,
                            content = post.content,
                            createdAt = post.createdAt
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProfileScreen() {
    SocialMediaTheme {
        val user = User(
            id = "2",
            name = "Gabriel",
            profilePicture = "",
            friends = listOf(
                User(id = "3", name = "Felipe", profilePicture = ""),
                User(id = "4", name = "Davi", profilePicture = ""),
                User(id = "5", name = "Lucas", profilePicture = "")
            ),
            posts = listOf(
                Post(
                    author = "Gabriel",
                    content = "Eu tinha feito muito isso pq eu dei uma consertada, mas tinha um q tava inglês, outro q tava em portguês tava uma loucura!",
                    createdAt = "25 min ago"
                ),
                Post(
                    author = "Gabriel",
                    content = "lmao lol ela é tão doidinhaaaa, such a craze😝, ela botou um cone na cabeça hahahahaha",
                    createdAt = "2 hours ago"
                ),
                Post(
                    author = "Gabriel",
                    content = "Dar enter na última linha é uma boa prática!",
                    createdAt = "1 min ago"
                ),
                Post(
                    author = "Gabriel",
                    content = "Eu tinha feito muito isso pq eu dei uma consertada, mas tinha um q tava inglês, outro q tava em portguês tava uma loucura!",
                    createdAt = "25 min ago"
                ),
                Post(
                    author = "Gabriel",
                    content = "lmao lol ela é tão doidinhaaaa, such a craze, ela botou um cone na cabeça hahahahaha",
                    createdAt = "2 hours ago"
                ),
                Post(
                    author = "Gabriel",
                    content = "Dar enter na última linha é uma boa prática!",
                    createdAt = "1 min ago"
                )
            )
        )
        ProfileScreen(
            uiState = ProfileUiState(user = user),
            onAddFriend = { },
            onBack = { }
        )
    }
}