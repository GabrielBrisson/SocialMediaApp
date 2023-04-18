package com.curral.social_media.presentation.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.curral.social_media.domain.model.User
import com.curral.social_media.ui.theme.SocialMediaTheme

@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    viewModel: UsersViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    UsersScreen(
        modifier = modifier,
        uiState = uiState,
        onSearch = viewModel::searchUsers,
        onAdd = viewModel::addUserAsFriend,
        onBack = onBack
    )
}

@Composable
internal fun UsersScreen(
    modifier: Modifier = Modifier,
    uiState: UsersUiState,
    onSearch: (q: String) -> Unit,
    onAdd: (id: String) -> Unit,
    onBack: () -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
        ) {
            // top bar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                var searchInput by remember { mutableStateOf("") }
                IconButton(onClick = onBack) {
                    Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
                }

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    value = searchInput,
                    onValueChange = { searchInput = it },
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = { onSearch(searchInput) }),
                )
            }

            // users
            LazyColumn(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth()
            ) {
                uiState.users?.let { users ->
                    item {
                        Text(
                            modifier = Modifier.padding(bottom = 10.dp, start = 0.dp),
                            text = "${users.size} Users found",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }

                    itemsIndexed(users) { index, user ->
                        if (index != 0) {
                            Divider(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                thickness = 0.8.dp,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                            )
                        }
                        FilteredUser(
                            modifier = Modifier.padding(vertical = 20.dp),
                            user = user,
                            onAdd = onAdd
                        )
                    }
                }
            }

        }
    }

}

@Preview
@Composable
fun PreviewUsersScreen() {
    SocialMediaTheme {
        val users = listOf(
            User(id = "1", name = "Guigui Guelerme", profilePicture = "asdasd"),
            User(id = "2", name = "Gabriel", profilePicture = ""),
            User(id = "3", name = "Felipe", profilePicture = ""),
            User(id = "4", name = "Davi", profilePicture = ""),
            User(id = "5", name = "Lucas", profilePicture = "")
        )
        UsersScreen(
            uiState = UsersUiState(users = users),
            onSearch = { },
            onAdd = { },
            onBack = { },
        )
    }
}