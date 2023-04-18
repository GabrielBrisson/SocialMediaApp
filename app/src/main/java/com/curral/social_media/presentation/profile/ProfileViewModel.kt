package com.curral.social_media.presentation.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curral.social_media.domain.repository.UserRepository
import com.curral.social_media.navigation.NavigationArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
) : ViewModel() {
    private val userId: String? = savedStateHandle[NavigationArgs.USER_ID]

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        userId?.let { getCurrentUser(it) }
    }

    private fun getCurrentUser(userId: String) {
        _uiState.update { it.copy(loading = true) }
        viewModelScope.launch {
            userRepository.getUserById(userId).collect { user ->
                _uiState.update { it.copy(user = user, loading = false) }
            }
        }
    }
}