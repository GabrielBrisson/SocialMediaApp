package com.curral.social_media.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curral.social_media.domain.repository.SharedPref
import com.curral.social_media.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val userRepository: UserRepository,
    sharedPref: SharedPref
) : ViewModel() {
    private val _currentUserId = sharedPref.getUserId()

    private val _uiState = MutableStateFlow(UsersUiState())
    val uiState = _uiState.asStateFlow()

    fun searchUsers(q: String) {
        _uiState.update { it.copy(usersLoading = true, usersError = null, users = null) }
        viewModelScope.launch {
            userRepository.getUserByName(
                name = q,
                onFailure = { errorMessage ->
                    _uiState.update { it.copy(usersLoading = false, usersError = errorMessage) }
                }
            ).collect { users ->
                _uiState.update { it.copy(usersLoading = false, users = users) }
            }
        }
    }

    fun addUserAsFriend(friendId: String) {
       _currentUserId?.let { currentUserId ->
           viewModelScope.launch {
               userRepository.createFriendship(currentUserId, friendId)
           }
       }
    }
}