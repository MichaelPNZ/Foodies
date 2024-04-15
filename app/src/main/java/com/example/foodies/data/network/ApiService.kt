package com.example.data.network

import com.example.data.network.dto.CategoryDTO
import com.example.data.network.dto.ProductDTO
import com.example.data.network.dto.TagDTO
import retrofit2.http.GET

interface ApiService {

    @GET("Categories.json")
    suspend fun getCategories(): List<CategoryDTO>

    @GET("Tags.json")
    suspend fun getTags(): List<TagDTO>

    @GET("Products.json")
    suspend fun getProducts(): List<ProductDTO>
}