package com.dashingqi.dqtimemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dashingqi.dqtimemanager.config.AppRoute
import com.dashingqi.dqtimemanager.ui.screen.MainView
import com.dashingqi.dqtimemanager.ui.screen.SplashView
import com.dashingqi.dqtimemanager.ui.theme.DQTimeManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DQTimeManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    NavManager()
                }
            }
        }
    }

    @Composable
    private fun NavManager() {
        // 创建一个导航控制器
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = AppRoute.SPLASH_LAUNCH_SCREEN) {
            composable(AppRoute.SPLASH_LAUNCH_SCREEN) {
                SplashView(navController)
            }
            composable(AppRoute.MAIN_SCREEN) {
                MainView()
            }
        }
    }
}