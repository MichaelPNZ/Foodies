package com.example.domain.repository

import com.example.domain.model.Catalog
import com.example.domain.model.Category
import com.example.domain.model.Product
import com.example.domain.model.Tag
import com.example.utils.LoadResource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getCategories(): Flow<LoadResource<List<Category>?>>
    fun getTags(): Flow<LoadResource<List<Tag>?>>
    fun getProducts(): Flow<LoadResource<List<Product>?>>
    fun getCatalog(): Flow<LoadResource<Catalog>?>
}