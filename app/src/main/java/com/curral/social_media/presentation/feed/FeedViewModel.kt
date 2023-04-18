package com.curral.social_media.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curral.social_media.domain.repository.SharedPref
import com.curral.social_media.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repository: UserRepository,
    sharedPref: SharedPref,
) : ViewModel() {
    private val _currentUserId = sharedPref.getUserId()

    private val _uiState = MutableStateFlow(FeedUiState())
    val uiState: StateFlow<FeedUiState> = _uiState.asStateFlow()

    init {
        if (!_currentUserId.isNullOrBlank()) {
            getCurrentUser(_currentUserId)
        }
    }

    private fun getCurrentUser(userId: String) {
        _uiState.update { it.copy(friendsLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUserById(userId).collect { user ->
                _uiState.update {
                    it.copy(
                        currentUser = user,
                        friends = user.friends,
                        friendsLoading = false
                    )
                }

            }
        }
    }

}