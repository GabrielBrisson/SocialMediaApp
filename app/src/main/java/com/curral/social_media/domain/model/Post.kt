package com.curral.social_media.domain.model

data class Post(
    val author: User,
    val content: String,
    val createdAt: String
)