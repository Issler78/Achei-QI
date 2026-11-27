package com.example.acheiqi.states

data class LoginFormUiState(
    // variaveis para o estado da tela
    val success: Boolean = false,
    val error: String? = null,
    val loading: Boolean = false,

    // variaveis do form
    val email: String = "",
    val password: String = ""
)
