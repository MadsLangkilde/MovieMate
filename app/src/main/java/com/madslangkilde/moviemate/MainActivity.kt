package com.madslangkilde.moviemate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.madslangkilde.moviemate.ui.MainScreen
import com.madslangkilde.ui_base.theme.MovieTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            MovieTheme {
                MainScreen(navController)
            }
        }
    }
}
