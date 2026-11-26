package com.example.acheiqi.states

data class RegisterFormUiState(
    // variaveis para o estado da tela
    val success: Boolean = false,
    val error: String? = null,
    val loading: Boolean = false,

    // variaveis do form
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val phone: String = "",
    val affiliation: String = "Escolha uma opção",
    val unit: String = "Escolha sua unidade"
)
