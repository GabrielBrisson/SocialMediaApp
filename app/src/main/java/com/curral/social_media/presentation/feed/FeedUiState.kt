package com.curral.social_media.presentation.feed

import com.curral.social_media.domain.model.Post
import com.curral.social_media.domain.model.User

data class FeedUiState(
    val loading: Boolean = true,
    val currentUser: User? = null,
    val friends: List<User>? = null,
    val posts: List<Post>? = null,
    val friendsError: Boolean = false
)
