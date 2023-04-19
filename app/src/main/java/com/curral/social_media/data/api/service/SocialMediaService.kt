package com.curral.social_media.data.api.service

import com.curral.social_media.data.api.dto.MessageResponse
import com.curral.social_media.data.api.dto.UserRegisterBody
import com.curral.social_media.data.api.dto.UserResponse
import com.curral.social_media.domain.model.Post
import com.curral.social_media.domain.model.User
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface SocialMediaService {

    @GET("usuarios/obterTodosUsuarios")
    suspend fun getUsers(): ApiResponse<List<UserResponse>>

    @GET("usuarios/obterPorId/{id}")
    suspend fun getUserById(@Path("id") id: String): ApiResponse<UserResponse>

    @GET("usuarios/obterUsuarioPorNome")
    suspend fun getUserByName(@Query("name") name: String): ApiResponse<List<UserResponse>>

    @GET("usuarios/obterPorId/{id}")
    suspend fun getFriends(@Path("id") id: String): ApiResponse<UserResponse>

    @POST("usuarios/registrarUsuario")
    suspend fun registerUser(@Body name: UserRegisterBody): ApiResponse<User>

    @POST("usuarios/criarMensagem/{id}")
    suspend fun createPost(@Path("id") id: String): ApiResponse<Post>

    @PUT("usuarios/atualizarUsuario/{id}")
    suspend fun updateUser(@Path("id") id: String): ApiResponse<User>

    @PUT("usuarios/realizarAmizade/{firstUserId}/{secondUserId}")
    suspend fun createFriendship(
        @Path("firstUserId") firstUserId: String,
        @Path("secondUserId") secondUserId: String
    )

    @DELETE("usuarios/removerAmizade/{firstUserId}/{removalUserId}")
    suspend fun removeFriendship(
        @Path("firstUserId") firstUserId: String,
        @Path("removalUserId") removalUserId: String
    )

    @DELETE("usuarios/removerUsuario/{userId}")
    suspend fun removeUser(@Path("userId") userId: String): ApiResponse<Nothing>

    @GET("obterUserFeed/{userId}")
    suspend fun getUserFeed(@Path("userId") userId: String): ApiResponse<List<MessageResponse>>
}