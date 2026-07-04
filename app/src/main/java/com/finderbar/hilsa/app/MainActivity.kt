package com.finderbar.hilsa.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.finderbar.hilsa.navigation.AppNavGraph
import com.finderbar.hilsa.core.ui.theme.AppTheme
import com.finderbar.hilsa.core.ui.theme.HilsaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HilsaTheme(themeType = AppTheme.ORANGE) {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
            }
        }
    }
}