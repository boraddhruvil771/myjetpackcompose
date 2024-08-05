package com.example.myjetpackcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp




/* example without state hoisting*/
@Composable
fun WithOutStateHoisting() {
    var text by remember { mutableStateOf("") }
    TextField(value = text, onValueChange = { text = it })

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WithStateHoistingExample(
    title: String,
    value: String,
    onValueChange: (String) -> Unit
) {

    TextField(value = value, onValueChange = onValueChange, placeholder = { Text(text = title) })
}

@Composable
fun StateHoisting(
    modifier: Modifier
) {

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        WithStateHoistingExample(
            title = stringResource(R.string.app_user_name),
            value = username,
            onValueChange = { username = it })

        Spacer(modifier = Modifier.height(20.dp))

        WithStateHoistingExample(
            title = stringResource(R.string.app_email),
            value = email,
            onValueChange = { email = it })

    }

}