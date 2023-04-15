package com.curral.social_media.data.api.repository

import com.curral.social_media.data.api.service.SocialMediaService
import com.curral.social_media.domain.model.ResultOf
import com.curral.social_media.domain.model.User
import com.curral.social_media.domain.repository.UserRepository

class UserRepositoryImpl(socialMediaService: SocialMediaService) : UserRepository{

    override suspend fun getUserById(id: Int): ResultOf<User> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchUser(query: String): ResultOf<List<User>> {
        TODO("Not yet implemented")
    }
}