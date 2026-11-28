package com.example.acheiqi.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acheiqi.client.ApiService
import com.example.acheiqi.client.RetrofitClient
import com.example.acheiqi.dtos.LoginRequestDTO
import com.example.acheiqi.dtos.LoginResponse
import com.example.acheiqi.states.LoginFormUiState
import com.example.acheiqi.utils.TokenManager
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(
    private val apiService: ApiService = RetrofitClient.apiService,
) : ViewModel() {
    var formState by mutableStateOf(LoginFormUiState())
        private set // dessa forma, apenas ESTA classe pode mudar os valores de formState, mesmo a variavel estando exposta a outros arquivos

    // funcao que recebe uma funcao como parametro para ser chamada
    fun updateForm(update: (LoginFormUiState) -> LoginFormUiState){
        formState = update(formState).copy(error = null)
    }

    fun clearError() {
        formState = formState.copy(error = null)
    }

    fun login(){
        // funcao para validar os campos
        if(!validate()) return

        // para fazer uma requisicao a uma api, precisamos envolver em uma funcao launch para a aplicacao nao parar de rodar enquanto a requisicao esta sendo enviada
        // launch nao pausa a thread principal da aplicacao, mantendo o front-end no loading enquanto a requisicao é feita
        viewModelScope.launch {
            formState = formState.copy(error = null, loading = true)
            try {
                val request = LoginRequestDTO(
                    email = formState.email.trim(),
                    password = formState.password.trim()
                )

                // chamando a api
                val response: Response<LoginResponse> = apiService.login(request)



                // verificar se a response deu certo / se ela retornou 200 ou deu erro
                if (response.isSuccessful && response.body()?.token != null){
                    formState = formState.copy(loading = false, success = true)

                    // salvar token no encrypted shared preferences
                    val token: String = response.body()!!.token

                    TokenManager.saveToken(token)
                } else {

                    val error = when (response.code()) {
                        400 -> "E-mail ou senha estão incorretos."
                        else -> "Erro ao realizar login."
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
            formState.email.trim().isBlank() ||
            !formState.email.trim().contains("@") ||
            formState.password.trim().isBlank()
        ){
            formState = formState.copy(error = "Por favor, preencha os campos corretamente.")
            return false
        }

        return true
    }
}