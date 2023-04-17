package com.curral.social_media.domain.repository

interface SharedPref {

    fun getUserId(): String?

    fun setUserId(id: String)
}