package com.curral.social_media.presentation.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
) {

    Column(modifier) {
        Text(text = label, color = Color.White)
        OutlinedTextField(
            modifier = Modifier.padding(top = 8.dp),
            value = value,
            onValueChange = onValueChanged,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White
            ),
        )

    }
}

@Preview
@Composable
fun PreviewLoginTextField() {
    MaterialTheme {
        LoginTextField(label = "Username", value = "", onValueChanged = { })
    }
}