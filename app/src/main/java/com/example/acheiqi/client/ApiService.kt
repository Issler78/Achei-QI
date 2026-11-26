package com.example.acheiqi.client

import com.example.acheiqi.dtos.RegisterRequestDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

// rotas que serao chamadas
interface ApiService {
    @POST("/v1/auth/register")
    suspend fun register(@Body request: RegisterRequestDTO) : Response<Unit>
}