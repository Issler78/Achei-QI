package com.example.acheiqi.states

import android.net.Uri

data class CreateItemFormUiState(
    // variaveis para o estado da tela
    val success: Boolean = false,
    val error: String? = null,
    val loading: Boolean = false,

    // variaveis do form
    val title: String = "",
    val description: String? = null,
    val category: String = "Escolha uma categoria",
    val unit: String = "Escolha uma unidade",
    val location: String = "",
    val image: Uri? = null,
)