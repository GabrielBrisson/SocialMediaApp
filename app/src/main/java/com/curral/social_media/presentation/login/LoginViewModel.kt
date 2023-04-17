package com.curral.social_media.presentation.login

import androidx.lifecycle.ViewModel
import com.curral.social_media.domain.repository.SharedPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPref: SharedPref
): ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun validateUsername(username: String) {
        username.ifEmpty {
            _uiState.update { it.copy(usernameInputError = "Username can't be empty") }
            return
        }


    }
}