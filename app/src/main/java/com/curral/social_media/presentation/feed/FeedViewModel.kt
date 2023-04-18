package com.curral.social_media.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curral.social_media.domain.repository.SharedPref
import com.curral.social_media.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
            getUserFeed(_currentUserId)
        }
    }

    private fun getCurrentUser(userId: String) {
        _uiState.update { it.copy(friendsLoading = true) }
        viewModelScope.launch {
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

    private fun getUserFeed(userId: String) {
        _uiState.update { it.copy(feedLoading = true, feed = null, feedError = null) }
        viewModelScope.launch {
            repository.getUserFeed(userId,
                onFailure = { errorMessage ->
                    _uiState.update {
                        it.copy(feedLoading = false, feed = null, feedError = errorMessage)
                    }
                }
            ).collect { feed ->
                _uiState.update { it.copy(feedLoading = false, feed = feed, feedError = null) }
            }
        }
    }

}