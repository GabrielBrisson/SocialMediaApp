package com.curral.social_media.domain.repository

import com.curral.social_media.domain.model.ResultOf
import com.curral.social_media.domain.model.User

interface UserRepository {

    suspend fun getUserById(id: Int): ResultOf<User>

    suspend fun fetchUser(query: String): ResultOf<List<User>>
}