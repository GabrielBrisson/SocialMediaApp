package com.curral.social_media.data.api.repository

import android.util.Log
import com.curral.social_media.data.api.dto.toDomain
import com.curral.social_media.data.api.service.SocialMediaService
import com.curral.social_media.domain.model.User
import com.curral.social_media.domain.repository.UserRepository
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(private val socialMediaService: SocialMediaService) : UserRepository{
    override suspend fun getAllUsers(): Flow<List<User>> = flow{
        val response = socialMediaService.getUsers()
        response.suspendOnSuccess{
            emit(data.map { it.toDomain() })
        }.onFailure {
            Log.d("erropai", "getAllUsers: ${message()}")
        }
    }

}