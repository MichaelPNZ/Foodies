package com.example.foodies.data.repository

import com.example.foodies.data.mapper.toCategory
import com.example.data.network.ApiService
import com.example.domain.model.Catalog
import com.example.domain.model.Category
import com.example.domain.model.Product
import com.example.domain.model.Tag
import com.example.domain.repository.ProductRepository
import com.example.foodies.data.mapper.toProduct
import com.example.foodies.data.mapper.toTag
import com.example.utils.LoadResource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
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
        return flow {
            try {
                val tags = apiService.getTags().map { it.toTag() }
                emit(LoadResource.Success(tags))
            } catch (e: Exception) {
                emit(LoadResource.Error("Ошибка загрузки тагов: ${e.message}"))
            }
        }
    }

    override fun getProducts(): Flow<LoadResource<List<Product>?>> {
        return flow {
            try {
                val products = apiService.getProducts().map { it.toProduct() }
                emit(LoadResource.Success(products))
            } catch (e: Exception) {
                emit(LoadResource.Error("Ошибка загрузки продуктов: ${e.message}"))
            }
        }
    }

    override fun getCatalog(): Flow<LoadResource<Catalog>?> {
        return flow {
            try {
                coroutineScope {
                    val categories = async {
                        apiService.getCategories().map { it.toCategory() }
                    }
                    val products = async {
                        apiService.getProducts().map { it.toProduct() }
                    }
                    val tags = async {
                        apiService.getTags().map { it.toTag() }
                    }
                    emit(
                        LoadResource.Success(
                            Catalog(
                                categories.await(),
                                products.await(),
                                tags.await()
                            )
                        )
                    )
                }
            } catch (e: Exception) {
                emit(LoadResource.Error("Ошибка загрузки каталога: ${e.message}"))
            }
        }
    }
}