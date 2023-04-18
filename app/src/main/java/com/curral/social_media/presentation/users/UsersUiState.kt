package com.curral.social_media.presentation.users

import com.curral.social_media.domain.model.User

data class UsersUiState(
    val usersLoading: Boolean = false,
    val users: List<User>? = null,
    val usersError: String? = null,
)