package com.curral.social_media.domain.repository

import com.curral.social_media.domain.model.Post
import com.curral.social_media.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getAllUsers():Flow<List<User>>

    suspend fun getUserById(id:String): Flow<User>

    suspend fun getFriends(id: String): Flow<List<User>>

    suspend fun registerUser(name:String): Flow<User>

    suspend fun createPost(userId: String): Flow<Post>
}