package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Home
            ) {
                composable<Home> {
                    HomeScreen(onShowGreeting = { typedName ->
                        navController.navigate(Greeting(userName = typedName))
                    })
                }

                composable<Greeting> { backStackEntry ->
                    val greeting: Greeting = backStackEntry.toRoute()
                    GreetingScreen(
                        userName = greeting.userName,
                        onNavigateBack = {
                            navController.popBackStack()

                        }
                    )
                }
            }
        }
    }
}