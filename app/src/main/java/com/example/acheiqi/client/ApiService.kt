package com.example.acheiqi.client

import com.example.acheiqi.dtos.CreateItemRequestDTO
import com.example.acheiqi.dtos.LoginRequestDTO
import com.example.acheiqi.dtos.LoginResponse
import com.example.acheiqi.dtos.RegisterRequestDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

// rotas que serao chamadas
interface ApiService {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequestDTO) : Response<Unit>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequestDTO) : Response<LoginResponse>

    @POST("items")
    suspend fun saveItem(@Body request: CreateItemRequestDTO) : Response<Unit>
}