package com.curral.social_media.presentation.post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.curral.social_media.ui.theme.SocialMediaTheme

@Composable
fun PostScreen(
    modifier: Modifier = Modifier,
    viewModel: PostViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    PostScreen(modifier = modifier, onPost = viewModel::post, onBack = onBack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PostScreen(
    modifier: Modifier = Modifier,
    onPost: (content: String) -> Unit,
    onBack: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "New Post", fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Close Post Button"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(12.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(370.dp),
                value = text,
                onValueChange = {
                    if (text.length < 250) {
                        text = it
                    }
                },
                textStyle = TextStyle(fontSize = 22.sp)
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "${text.length}/250",
                color = MaterialTheme.colorScheme.onSurface
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 22.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ElevatedButton(onClick = { text = "" }) {
                    Text(
                        modifier = Modifier
                            .width(100.dp),
                        text = "Cancel",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Button(onClick = { onPost(text); onBack() }) {
                    Text(
                        modifier = Modifier
                            .width(100.dp),
                        text = "Send",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPostScreen() {
    SocialMediaTheme() {
        PostScreen(onBack = {}, onPost = {})
    }
}