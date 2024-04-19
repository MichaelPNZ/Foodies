package com.example.foodies.data.mapper

import com.example.foodies.data.network.dto.TagDTO
import com.example.domain.model.Tag

fun TagDTO.toTag(): Tag {
    return Tag(
        id = id ?: 0,
        name = name ?: "",
    )
}