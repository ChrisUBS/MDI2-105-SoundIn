package com.example.mdi2_105_soundin.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.Navigator
import com.example.mdi2_105_soundin.ui.theme.MDI2105SoundInTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginContent(
    paddingValues: PaddingValues,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    onNavigateToRegister: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberSession by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()), // allow scrolling of the content
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "S",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary
        ) // end of text
        Text(
            text = "SoundIn",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = false
            },
            label = {
                Text(text = "Email Address")
            },
            isError = emailError,
            supportingText = {
                if (emailError) {
                    Text(text = "Please enter a valid email address")
                }
            }, // end of supporting text
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        ) // end of outlined text field

        var passwordVisible by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = false
            },
            label = {
                Text(text = "Password")
            },
            isError = passwordError,
            supportingText = {
                if (passwordError) {
                    Text(text = "Please enter a valid password")
                }
            }, // end of supporting text
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(
                        imageVector = if (passwordVisible) {
                            Icons.Default.Visibility
                        } else {
                            Icons.Default.VisibilityOff
                        },
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            } // end of trailing icon
        ) // end of outlined text field
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Remember me",
                style = MaterialTheme.typography.bodyMedium
            )
            Switch(
                checked = rememberSession,
                onCheckedChange = {
                    rememberSession = it
                }
            )
        } // end of row
        Button(
            onClick = {
                emailError = !email.contains("@") || !email.contains(".")
                passwordError = password.length < 8 // or any other validation
                if(emailError || passwordError) {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Please review the marked fields",
                            actionLabel = "Dismiss",
                            duration = SnackbarDuration.Short
                        )
                    } // end of snackbar
                } else {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Welcome to SoundIn",
                            actionLabel = "Dismiss",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            }, // end of on click
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log In")
        }
        TextButton( onClick = onNavigateToRegister ) {
            Text("Don't have an account? Register")
        }
    } // end of column
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SoundIn") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        paddingValues ->
        LoginContent(
            paddingValues = paddingValues,
            scope = scope,
            snackbarHostState = snackbarHostState,
            onNavigateToRegister = onNavigateToRegister
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    MDI2105SoundInTheme() {
        LoginContent(
            paddingValues = PaddingValues(0.dp),
            scope = rememberCoroutineScope(),
            snackbarHostState = remember { SnackbarHostState() },
            onNavigateToRegister = {}
        )
    }
}