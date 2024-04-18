package com.example.domain.usecases.catalog_use_cases

import com.example.domain.repository.ProductRepository
import javax.inject.Inject

class GetTagsUseCase @Inject constructor(
    private val  repository: ProductRepository
) {
    operator fun invoke() = repository.getTags()
}