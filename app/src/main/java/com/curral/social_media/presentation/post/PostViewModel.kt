package com.curral.social_media.presentation.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curral.social_media.domain.repository.SharedPref
import com.curral.social_media.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val userRepository: UserRepository,
    sharedPref: SharedPref,
) : ViewModel() {
    private val _currentUserId = sharedPref.getUserId()
    fun post(content: String) {
        if (content.isNotEmpty() && !_currentUserId.isNullOrEmpty()) {
            viewModelScope.launch {
                userRepository.createPost(_currentUserId, content)
            }
        }
    }
}