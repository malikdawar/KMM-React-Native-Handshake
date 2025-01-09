package com.kmm.kmpsharedmodule

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.PreComposeApp

@Composable
internal fun App() {
    PreComposeApp {
        MaterialTheme {
            LoginScreen()
        }
    }
}

@Composable
fun LoginScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        
        var onBtnClicked by remember { mutableStateOf("Login") }

        Text(onBtnClicked, modifier = Modifier.fillMaxWidth().size(44.dp))
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "",
            onValueChange = { println(it) },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "",
            onValueChange = { println(it) },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onBtnClicked = "Login Button Clicked" },
            enabled = true,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}
