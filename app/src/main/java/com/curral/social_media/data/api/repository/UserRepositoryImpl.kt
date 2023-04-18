package com.curral.social_media.data.api.repository

import android.util.Log
import com.curral.social_media.data.api.dto.UserRegisterBody
import com.curral.social_media.data.api.dto.toDomain
import com.curral.social_media.data.api.service.SocialMediaService
import com.curral.social_media.domain.model.Post
import com.curral.social_media.domain.model.User
import com.curral.social_media.domain.repository.UserRepository
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(private val socialMediaService: SocialMediaService) : UserRepository {
    override suspend fun getAllUsers(): Flow<List<User>> = flow {
        val response = socialMediaService.getUsers()
        response.suspendOnSuccess {
            emit(data.map { it.toDomain() })
        }.onFailure {
            Log.e(TAG, "getAllUsers failure: ${message()}")
        }
    }

    override suspend fun getUserById(id: String): Flow<User> = flow {
        val response = socialMediaService.getUserById(id)
        response.suspendOnSuccess {
            emit(data.toDomain())
        }.onFailure {

            Log.e(TAG, "getUserById failure: ${message()}")
        }
    }

    override suspend fun getFriends(id: String): Flow<User> = flow {
        val response = socialMediaService.getFriends(id)
        response.suspendOnSuccess {
            emit(data.toDomain())
        }.onFailure {
            Log.e(TAG, "getFriends failure: ${message()}")
        }
    }

    override suspend fun registerUser(name: String) = flow {
        val response = socialMediaService.registerUser(UserRegisterBody(name))
        response.suspendOnSuccess {
            emit(data)
        }.onFailure {
            Log.e(TAG, "registerUser failure: ${message()}")
        }
    }

    override suspend fun createPost(userId: String): Flow<Post> = flow {
        val response = socialMediaService.createPost(userId)
        response.suspendOnSuccess {
            emit(data)
        }.onFailure {
            Log.e(TAG, "createPost failure: ${message()}")
        }
    }

    override suspend fun updateUser(userId: String): Flow<User> = flow{
        val response = socialMediaService.updateUser(userId)
        response.suspendOnSuccess {
            emit(data)
        }.onFailure {
            Log.e(TAG, "updateUser failure: ${message()}")
        }
    }

    override suspend fun createFriendship(firstUserId: String, secondUserId: String) {
        socialMediaService.createFriendship(firstUserId, secondUserId)
    }

    override suspend fun removeFriendship(firstUserId: String, removalUserId: String) {
        socialMediaService.removeFriendship(firstUserId, removalUserId)
    }

    override suspend fun removeUser(userId: String) {
         socialMediaService.removeUser(userId)
    }

    companion object {
        val TAG = UserRepositoryImpl::class.simpleName
    }

}