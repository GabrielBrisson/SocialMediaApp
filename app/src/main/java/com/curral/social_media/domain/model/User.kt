package com.curral.social_media.domain.model

data class User(
    val id: String,
    val name: String,
    val profilePicture: String? = null,
    val friends: List<User>? = null,
    val posts: List<Post>? = null
)
