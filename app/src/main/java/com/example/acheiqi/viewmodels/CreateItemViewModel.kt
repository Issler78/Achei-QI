package com.example.acheiqi.viewmodels

import android.content.Context
import android.net.Uri
import android.util.Base64
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acheiqi.client.ApiService
import com.example.acheiqi.client.RetrofitClient
import com.example.acheiqi.dtos.CreateItemRequestDTO
import com.example.acheiqi.states.CreateItemFormUiState
import kotlinx.coroutines.launch

class CreateItemViewModel(
    private val apiService: ApiService = RetrofitClient.apiService,
) : ViewModel() {
    var formState by mutableStateOf(CreateItemFormUiState())
        private set // dessa forma, apenas ESTA classe pode mudar os valores de formState, mesmo a variavel estando exposta a outros arquivos

    // funcao que recebe uma funcao como parametro para ser chamada
    fun updateForm(update: (CreateItemFormUiState) -> CreateItemFormUiState){
        formState = update(formState).copy(error = null)
    }

    fun clearError() {
        formState = formState.copy(error = null)
    }

    fun save(context: Context){
        // funcao para validar os campos
        if(!validate()) return

        // funcao para transformar a uri do input de file para base64 (para passar de forma segura para a API do back-end)
        val imageBase64 = uriToBase64(context, formState.image!!)
        if(imageBase64 == null){
            formState = formState.copy(error = "Erro ao tentar enviar imagem", loading = false)
            return
        }



        // para fazer uma requisicao a uma api, precisamos envolver em uma funcao launch para a aplicacao nao parar de rodar enquanto a requisicao esta sendo enviada
        // launch nao pausa a thread principal da aplicacao, mantendo o front-end no loading enquanto a requisicao é feita
        viewModelScope.launch {
            formState = formState.copy(error = null, loading = true)
            try {
                val request = CreateItemRequestDTO(
                    title = formState.title,
                    description = formState.description,
                    category = formState.category,
                    imageBase64 = imageBase64,
                    location = formState.location,
                    unit = formState.unit
                )

                // chamando a api
                val response = apiService.saveItem(request)

                // verificar se a response deu certo / se ela retornou 201/CREATED ou deu erro
                if (response.code() == 201){
                    formState = formState.copy(loading = false, success = true)
                } else {
                    val error = when (response.code()) {
                        400 -> "Campos incorretos."
                        401 -> "Faça o login para continuar."
                        else -> "Erro ao salvar objeto."
                    }
                    formState = formState.copy(loading = false, error = error)
                }

            } catch (e: Exception) {
                formState = formState.copy(error = "Erro: ${e.message}", loading = false)
            }
        }
    }

    private fun validate() : Boolean{
        if(
            formState.title.trim().isBlank() ||
            formState.category == "Escolha uma categoria" ||
            formState.unit == "Escolha uma unidade" ||
            formState.location.trim().isBlank() ||
            formState.image == null
        ){
            formState = formState.copy(error = "Por favor, preencha os campos corretamente.")
            return false
        }

        return true
    }

    private fun uriToBase64(context: Context, uri: Uri) : String? {
        val bytes = context.contentResolver.openInputStream(uri)?.use { it.readBytes() } ?: return null
        return Base64.encodeToString(bytes, Base64.NO_WRAP)
    }
}