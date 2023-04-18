package com.curral.social_media.presentation.feed

import com.curral.social_media.domain.model.Post
import com.curral.social_media.domain.model.User

data class FeedUiState(
    val loading: Boolean = false,
    val currentUser: User? = null,
    val posts: List<Post>? = null,

    val friendsLoading: Boolean = false,
    val friends: List<User>? = null,
    val friendsError: String? = null
)
