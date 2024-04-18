package com.example.domain.usecases

import com.example.domain.repository.ProductRepository
import javax.inject.Inject

class GetCatalogUseCase @Inject constructor(
    private val  repository: ProductRepository
) {
    operator fun invoke() = repository.getCatalog()
}