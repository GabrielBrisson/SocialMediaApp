package com.curral.social_media.data.api.dto

import com.curral.social_media.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    val id: String,
    val name: String,
    @SerializedName("message")
    val messages: List<MessageResponse>?,
    @SerializedName("amigo")
    val friends: List<UserResponse>?
)

fun UserResponse.toDomain(): User =
    User(
        id = id,
        name = name,
        profilePicture = null,
        friends = friends?.map { it.toDomain() },
        posts = messages?.map { it.toDomain() })
