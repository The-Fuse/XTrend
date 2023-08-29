package com.rohit.xtrend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rohit.xtrend.screens.CategoryScreen
import com.rohit.xtrend.screens.detailsScreen
import com.rohit.xtrend.ui.theme.XTrendTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XTrendTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "XTrend")
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = Color.Black,
                                titleContentColor = Color.White
                            )
                        )
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        app()
                    }
                }
            }
        }
    }

    @Composable
    fun app() {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "category") {
            composable("category") {
                CategoryScreen {
                    navController.navigate("details/${it}")
                }
            }
            composable("details/{category}", arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )) {

                detailsScreen()
            }
        }
    }
}
