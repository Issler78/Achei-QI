package com.example.acheiqi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.acheiqi.components.TopBar
import com.example.acheiqi.ui.theme.Background

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            Scaffold(
                modifier = Modifier.fillMaxSize().background(color = Background),
                topBar = { TopBar(navController = navController) }
            ) { innerPadding ->

                // mudar depois a rota inicial para a tela inicial
                NavHost(
                    navController = navController,
                    startDestination = "register",
                    modifier = Modifier.padding(innerPadding)
                ){
                    composable(route = "register") {
                        RegisterPage(navController = navController)
                    }
                }
            }
        }
    }
}