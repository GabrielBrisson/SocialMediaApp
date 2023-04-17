package com.curral.social_media.data.api.dto

import com.curral.social_media.domain.model.Post
import com.google.gson.annotations.SerializedName

data class MessageResponse(
    val value: String,
    @SerializedName("dataCriacao")
    val createdAt: String
)

fun MessageResponse.toDomain():Post = Post(author = null, content = value, createdAt = createdAt)