package com.example.data.network.dto

import com.google.gson.annotations.SerializedName

data class TagDTO(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null
)