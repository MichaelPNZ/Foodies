package com.example.data.mapper

import com.example.data.network.dto.CategoryDTO
import com.example.domain.model.Category

fun CategoryDTO.toCategory(): Category {
    return Category(
        id = id ?: 0,
        name = name ?: "",
    )
}