package com.curral.social_media.data.api.service

import com.curral.social_media.data.api.dto.UserResponse
import com.curral.social_media.domain.model.Post
import com.curral.social_media.domain.model.User
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SocialMediaService {

    @GET("usuarios/obterTodosUsuarios")
    suspend fun getUsers(): ApiResponse<List<UserResponse>>

    @GET("usuarios/obterPorId/{id}")
    suspend fun getUserById(@Path("id") id: String): ApiResponse<UserResponse>

    @GET("usuarios/obterPorId/{id}")
    suspend fun getFriends(@Path("id") id : String): ApiResponse<List<UserResponse>>

    @POST("usuarios/registrarUsuario")
    suspend fun registerUser(name: String): ApiResponse<User>

    @POST("usuarios/criarMensagem/{id}")
    suspend fun createPost(@Path("id") id: String): ApiResponse<Post>
}