package com.example.acheiqi.dtos

data class CreateItemRequestDTO(
    val title: String,
    val description: String?,
    val category: String,
    val imageBase64: String,
    val location: String,
    val unit: String
)
