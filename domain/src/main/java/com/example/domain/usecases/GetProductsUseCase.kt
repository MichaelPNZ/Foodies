package com.example.domain.usecases

import com.example.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val  repository: ProductRepository
) {
    operator fun invoke() = repository.getProducts()
}