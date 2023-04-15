package com.curral.social_media.data.api.service

import com.curral.social_media.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface SocialMediaService {

    @GET("user/{userId}")
    suspend fun getUserById(@Path("userId") userId: Int): User
}