package com.example.acheiqi.utils

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // criando / pegando o http request
        val request = chain.request().newBuilder()
        val token: String? = TokenManager.getToken()

        if(token != null){
            // se tiver um token no shared preferences, utilizar ele em todas as requisicoes no retrofit no header dos requests
            request.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(request.build())
    }

}