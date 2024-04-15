package com.example.data.repository

import com.example.data.mapper.toCategory
import com.example.data.network.ApiService
import com.example.foodies.domain.model.Category
import com.example.foodies.domain.model.Product
import com.example.foodies.domain.model.Tag
import com.example.foodies.domain.repository.ProductRepository
import com.example.foodies.utils.LoadResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProductRepository {

    override fun getCategories(): Flow<LoadResource<List<Category>?>> {
        return flow {
            try {
                val categories = apiService.getCategories().map { it.toCategory() }
                emit(LoadResource.Success(categories))
            } catch (e: Exception) {
                emit(LoadResource.Error("Ошибка загрузки категорий: ${e.message}"))
            }
        }
    }

    override fun getTags(): Flow<LoadResource<List<Tag>?>> {
        TODO("Not yet implemented")
    }

    override fun getProducts(): Flow<LoadResource<List<Product>?>> {
        TODO("Not yet implemented")
    }

}