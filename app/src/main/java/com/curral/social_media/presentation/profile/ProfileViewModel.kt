package com.curral.social_media.presentation.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curral.social_media.domain.model.ResultOf
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
    private val userId: Int? = savedStateHandle[NavigationArgs.USER_ID]

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        userId?.let { fetchUserById(it) }
    }

    private fun fetchUserById(id: Int) {
        _uiState.update { it.copy(loading = true, error = null, user = null) }
        viewModelScope.launch {
            when (val result = userRepository.getUserById(id)) {
                is ResultOf.Failure -> {
                    _uiState.update { it.copy(loading = false, error = result.throwable) }
                }
                is ResultOf.Success -> {
                    _uiState.update { it.copy(loading = false, user = result.data) }
                }
            }
        }
    }

}