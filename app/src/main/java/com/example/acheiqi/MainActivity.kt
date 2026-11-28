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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.acheiqi.components.TopBar
import com.example.acheiqi.ui.theme.Background
import com.example.acheiqi.utils.TokenManager
import com.example.acheiqi.viewmodels.CreateItemViewModel
import com.example.acheiqi.viewmodels.LoginViewModel
import com.example.acheiqi.viewmodels.RegisterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        TokenManager.init(this)

        setContent {
            val navController = rememberNavController()
            Scaffold(
                modifier = Modifier.fillMaxSize().background(color = Background),
                topBar = { TopBar(navController = navController) }
            ) { innerPadding ->

                // mudar depois a rota inicial para a tela inicial
                NavHost(
                    navController = navController,
                    startDestination = "home",
                    modifier = Modifier.padding(innerPadding)
                ){
                    composable(route = "register") {
                        val registerViewModel: RegisterViewModel = viewModel()
                        RegisterPage(navController = navController, viewModel = registerViewModel)
                    }

                    composable(route = "login") {
                        val loginViewModel: LoginViewModel = viewModel()
                        LoginPage(navController = navController, viewModel = loginViewModel)
                    }

                    composable(route = "home") {
                        val createItemViewModel: CreateItemViewModel = viewModel()
                        HomePage(navController = navController, createItemViewModel = createItemViewModel)
                    }
                }
            }
        }
    }
}