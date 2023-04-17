package com.curral.social_media.domain.repository

import com.curral.social_media.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getAllUsers():Flow<List<User>>
}