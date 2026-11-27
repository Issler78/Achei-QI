package com.example.acheiqi.client

import com.example.acheiqi.utils.TokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // IMPORTANTE !!!
    // PARA A URL, SE ESTIVER UTILIZANDO EMULADOR, UTILIZAR O ENDEREÇO 10.0.2.2 + PORTA DA APLICACAO BACK END
    // SE ESTIVER UTILIZANDO CELULAR FISICO POR CABO OU WIFI, UTILIZAR O ENDEREÇO IP DA MÁQUINA NA REDE + PORTA DA APLICACAO BACK END

    private const val URL = "http://10.0.2.2:8090/v1/"

    // com o interceptor, se houver token, ele sera adicionado no header Authorization das requests
    private val tokenInterceptor = OkHttpClient.Builder()
        .addInterceptor(TokenInterceptor())
        .build()

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(URL)
            .client(tokenInterceptor)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}