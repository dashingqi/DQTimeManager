package com.dashingqi.dqtimemanager.ui.screen

/**
 * @desc : 主页面
 * @author : zhangqi
 * @time : 2023/8/27 15:44
 */
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.TimerOff
import androidx.compose.material.icons.filled.WatchOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dashingqi.dqtimemanager.config.MainPageRoute
import com.dashingqi.dqtimemanager.ui.screen.main.MainCountDownPageView
import com.dashingqi.dqtimemanager.ui.screen.main.MainStopWatchPageView
import com.dashingqi.dqtimemanager.ui.screen.main.MainTimePageView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPageView() {
    val bottomItems = listOf(
        Pair("主页", Icons.Default.Home),
        Pair("倒计时", Icons.Default.TimerOff),
        Pair("秒表", Icons.Default.WatchOff)
    )

    var navSelectedState by remember {
        mutableIntStateOf(0)
    }

    val navHostController = rememberNavController()
    navHostController.addOnDestinationChangedListener { _, desc, _ ->
        when (desc.route) {
            MainPageRoute.MAIN_TIME_SCREEN -> {
                navSelectedState = 0
            }

            MainPageRoute.MAIN_COUNT_DOWN_SCREEN -> {
                navSelectedState = 1
            }

            MainPageRoute.MAIN_STOP_WATCH_SCREEN -> {
                navSelectedState = 2
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NavigationBar {
            bottomItems.forEachIndexed { index, pair ->
                NavigationBarItem(selected = navSelectedState == index,
                    onClick = {
                        navSelectedState = index
                        navHostController.apply {
                            when (index) {
                                0 -> navToMainPage(MainPageRoute.MAIN_TIME_SCREEN)
                                1 -> navToMainPage(MainPageRoute.MAIN_COUNT_DOWN_SCREEN)
                                2 -> navToMainPage(MainPageRoute.MAIN_STOP_WATCH_SCREEN)
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = pair.second,
                            contentDescription = pair.first
                        )
                    }, label = {
                        Text(text = pair.first)
                    })
            }
        }
    }) {
        Box(modifier = Modifier.padding(it)) {
            MainPageNavManager(navHostController)
        }
    }
}

/**
 * 主页面大航管理器
 */
@Composable
private fun MainPageNavManager(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = MainPageRoute.MAIN_TIME_SCREEN) {
        composable(MainPageRoute.MAIN_TIME_SCREEN) {
            MainTimePageView()
        }
        composable(MainPageRoute.MAIN_COUNT_DOWN_SCREEN) {
            MainCountDownPageView()
        }
        composable(MainPageRoute.MAIN_STOP_WATCH_SCREEN) {
            MainStopWatchPageView()
        }
    }
}

fun NavHostController.navToMainPage(routePage: String) {
    this.navigate(routePage) {
        popUpTo(this@navToMainPage.graph.findStartDestination().id)
        launchSingleTop = true
    }
}

@Preview
@Composable
fun MainViewPreview() {
    MainPageView()
}

