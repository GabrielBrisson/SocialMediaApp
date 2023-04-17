package com.curral.social_media.domain.model

//Todo: completar o user
data class User(
    val id: String,
    val name: String,
    val profilePicture: String?,
    val friends: List<User>? = null,
    val posts: List<Post>? = null
)
