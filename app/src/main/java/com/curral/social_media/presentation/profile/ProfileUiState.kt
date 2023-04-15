package com.curral.social_media.presentation.profile

import com.curral.social_media.domain.model.User

data class ProfileUiState(
    val loading: Boolean = false,
    val user: User? = null,
    val error: Throwable? = null,
)
