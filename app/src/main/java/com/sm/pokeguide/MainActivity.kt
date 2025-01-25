package com.sm.pokeguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var keepSplashScreen = true
        lifecycleScope.launch {
            delay(4000) // Simulate some work
            keepSplashScreen = false
        }

        splashScreen.setKeepOnScreenCondition { keepSplashScreen }
        enableEdgeToEdge()

        setContent {
            MainPokeUi()
        }
    }
}
