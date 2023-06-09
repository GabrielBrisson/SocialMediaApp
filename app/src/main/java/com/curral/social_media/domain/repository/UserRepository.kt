package com.curral.social_media.domain.repository

import com.curral.social_media.domain.model.Post
import com.curral.social_media.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getAllUsers():Flow<List<User>>

    suspend fun getUserById(id:String): Flow<User>

    suspend fun getUserByName(name: String, onFailure: (message: String) -> Unit): Flow<List<User>>

    suspend fun getFriends(id: String): Flow<User>

    suspend fun registerUser(name:String): Flow<User>

    suspend fun createPost(userId: String, content: String)

    suspend fun updateUser(userId: String): Flow<User>

    suspend fun createFriendship(firstUserId: String, secondUserId: String)

    suspend fun removeFriendship(firstUserId: String, removalUserId: String)

    suspend fun removeUser(userId: String)

    suspend fun getUserFeed(userId: String, onFailure: (message: String) -> Unit): Flow<List<Post>>
}