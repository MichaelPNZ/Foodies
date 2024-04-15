package com.example.foodies.domain.usecases

import com.example.foodies.domain.repository.ProductRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val  repository: ProductRepository
) {
    operator fun invoke() = repository.getCategories()
}