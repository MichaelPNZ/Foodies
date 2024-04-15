package com.example.foodies.data.mapper

import com.example.data.network.dto.TagDTO
import com.example.foodies.domain.model.Tag

fun TagDTO.toTag(): Tag {
    return Tag(
        id = id ?: 0,
        name = name ?: "",
    )
}