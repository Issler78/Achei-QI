package com.example.acheiqi.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acheiqi.client.ApiService
import com.example.acheiqi.client.RetrofitClient
import com.example.acheiqi.dtos.RegisterRequestDTO
import com.example.acheiqi.states.RegisterFormUiState
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val apiService: ApiService = RetrofitClient.apiService
) : ViewModel() {
    var formState by mutableStateOf(RegisterFormUiState())
        private set // dessa forma, apenas ESTA classe pode mudar os valores de formState, mesmo a variavel estando exposta a outros arquivos

    // funcao que recebe uma funcao como parametro para ser chamada
    fun updateForm(update: (RegisterFormUiState) -> RegisterFormUiState){
        formState = update(formState).copy(error = null)
    }

    fun clearError() {
        formState = formState.copy(error = null)
    }

    fun register(){
        // funcao para validar os campos
        if(!validate()) return

        // para fazer uma requisicao a uma api, precisamos envolver em uma funcao launch para a aplicacao nao parar de rodar enquanto a requisicao esta sendo enviada
        // launch nao pausa a thread principal da aplicacao, mantendo o front-end no loading enquanto a requisicao é feita
        viewModelScope.launch {
            formState = formState.copy(error = null, loading = true)
            try {
                val request = RegisterRequestDTO(
                    name = formState.name.trim(),
                    email = formState.email.trim(),
                    password = formState.password.trim(),
                    phone = formState.phone.trim(),
                    qiAffiliation = formState.affiliation.trim(),
                    qiUnit = formState.unit.trim()
                )

                // chamando a api
                val response = apiService.register(request)

                // verificar se a response deu certo / se ela retornou 200 ou deu erro
                if (response.isSuccessful){
                    formState = formState.copy(loading = false, success = true)
                } else {
                    val error = when (response.code()) {
                        400 -> "Este e-mail já está cadastrado."
                        else -> "Erro ao realizar cadastro."
                    }
                    val errorBody = response.errorBody()?.string()
                    android.util.Log.e("REGISTER_ERROR", "HTTP ${response.code()}: $errorBody")
                    formState = formState.copy(loading = false, error = error)

                }
            } catch (e: Exception) {
                formState = formState.copy(error = "Erro: ${e.message}", loading = false)
            }
        }
    }

    private fun validate() : Boolean{
        if(
            formState.name.trim().isBlank() ||
            formState.email.trim().isBlank() ||
            !formState.email.trim().contains("@") ||
            formState.password.trim().isBlank() ||
            formState.password.trim().length < 6 ||
            formState.phone.trim().isBlank() ||
            formState.phone.trim().length != 11 ||
            formState.affiliation == "Escolha uma opção" ||
            formState.unit == "Escolha sua unidade"
        ){
            formState = formState.copy(error = "Por favor, preencha os campos corretamente.")
            return false
        }

        return true
    }
}