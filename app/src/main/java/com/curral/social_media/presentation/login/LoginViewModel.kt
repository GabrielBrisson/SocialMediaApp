package com.curral.social_media.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curral.social_media.domain.repository.SharedPref
import com.curral.social_media.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPref: SharedPref,
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun validateUsername(username: String, onValid: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            username.ifEmpty {
                _uiState.update {
                    it.copy(usernameInputError = "Username can't be empty")
                }
                return@launch
            }
            userRepository.registerUser(username).collect { user ->
                sharedPref.setUserId(user.id)
                _uiState.update { it.copy(usernameInputError = null) }
                onValid()
            }
        }
    }
}