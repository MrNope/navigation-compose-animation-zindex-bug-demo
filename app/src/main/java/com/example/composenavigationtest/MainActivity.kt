package com.example.composenavigationtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenavigationtest.ui.theme.ComposeNavigationTestTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNavigationTestTheme {
                val controller = rememberNavController()
                NavHost(controller, "a",
                    enterTransition = {
                        // Slide up 100%
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Up,
                            tween(1000)
                        )
                    },
                    exitTransition = {
                        // Slide up 10%
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Up,
                            tween(1000),
                            targetOffset = { (it * 0.1).roundToInt() }
                        )
                    },
                    popEnterTransition = {
                        // Slide down 10%
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Down,
                            tween(1000),
                            initialOffset = { (it * 0.1).roundToInt() }
                        )
                    },
                    popExitTransition = {
                        // Slide down 100%
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Down,
                            tween(1000)
                        )
                    },
                ) {
                    composable("a") {
                        Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color(0xffccffff)) { innerPadding ->
                            Column(Modifier.padding(innerPadding).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                Button(onClick = { controller.navigate("b") }) {
                                    Text("Open B")
                                }
                                OutlinedButton(onClick = { controller.popBackStack() }) {
                                    Text("back")
                                }
                            }
                        }
                    }
                    composable("b") {
                        Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color(0xffffffcc)) { innerPadding ->
                            Column(Modifier.padding(innerPadding).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                Button(onClick = { controller.navigate("a") }) {
                                    Text("Open A")
                                }
                                OutlinedButton(onClick = { controller.popBackStack() }) {
                                    Text("back")
                                }
                            }
                        }
                    }
                }


            }
        }
    }
}
