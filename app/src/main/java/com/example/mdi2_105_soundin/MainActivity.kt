package com.example.mdi2_105_soundin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mdi2_105_soundin.ui.screens.LoginContent
import com.example.mdi2_105_soundin.ui.screens.LoginScreen
import com.example.mdi2_105_soundin.ui.theme.MDI2105SoundInTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MDI2105SoundInTheme {
                Surface( // Our personalized theme
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(
                        onNavigateToRegister = {}
                    )
                } // end of surface
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SoundInPreview() {
    MDI2105SoundInTheme() {
        LoginScreen(
            onNavigateToRegister = {}
        )
    }
}