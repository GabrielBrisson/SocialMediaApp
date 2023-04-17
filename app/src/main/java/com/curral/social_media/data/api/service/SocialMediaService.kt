package com.curral.social_media.data.api.service

import com.curral.social_media.data.api.dto.UserResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface SocialMediaService {

    @GET("usuarios/obterTodosUsuarios")
    suspend fun getUsers(): ApiResponse<List<UserResponse>>
}