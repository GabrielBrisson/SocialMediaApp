package com.curral.social_media.presentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.curral.social_media.ui.theme.SocialMediaTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    ProfileScreen(modifier = modifier, uiState = uiState, onBack = onBack)
}

@Composable
internal fun ProfileScreen(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState,
    onBack: () -> Unit,
) {

}

@Preview
@Composable
fun PreviewProfileScreen() {
    SocialMediaTheme {
        ProfileScreen(uiState = ProfileUiState(), onBack = { })
    }
}