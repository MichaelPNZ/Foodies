package com.example.foodies.domain.repository

import com.example.foodies.domain.model.Catalog
import com.example.foodies.domain.model.Category
import com.example.foodies.domain.model.Product
import com.example.foodies.domain.model.Tag
import com.example.foodies.utils.LoadResource
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getCategories(): Flow<LoadResource<List<Category>?>>
    fun getTags(): Flow<LoadResource<List<Tag>?>>
    fun getProducts(): Flow<LoadResource<List<Product>?>>
    fun getCatalog(): Flow<LoadResource<Catalog>?>
}