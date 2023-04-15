package com.curral.social_media.presentation.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.curral.social_media.ui.theme.SocialMediaTheme

@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = hiltViewModel(),
    onProfile: (id: Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    FeedScreen(
        modifier = modifier,
        uiState = uiState,
        onProfile = onProfile
    )
}

@Composable
internal fun FeedScreen(
    modifier: Modifier = Modifier,
    uiState: FeedUiState,
    onProfile: (id: Int) -> Unit,
) {

}

@Preview
@Composable
fun PreviewFeedScreen() {
    SocialMediaTheme {
        FeedScreen(
            uiState = FeedUiState(),
            onProfile = { }
        )

    }
}