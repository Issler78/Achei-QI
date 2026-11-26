package com.example.acheiqi.dtos

data class RegisterRequestDTO(
    val name: String,
    val email: String,
    val password: String,
    val phone: String,
    val qiAffiliation: String,
    val qiUnit: String
)
