package com.curral.social_media.presentation.users

import androidx.lifecycle.ViewModel
import com.curral.social_media.domain.repository.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(sharedPref: SharedPref) : ViewModel() {
    private val _currentUserId = sharedPref.getUserId()

    private val _uiState = MutableStateFlow(UsersUiState())
    val uiState = _uiState.asStateFlow()

    fun searchUsers(q: String) {
        //todo
    }

    fun addUserAsFriend(friendId: String) {
       //todo
    }
}