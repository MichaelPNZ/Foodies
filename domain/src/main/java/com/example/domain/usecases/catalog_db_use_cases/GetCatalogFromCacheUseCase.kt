package com.example.domain.usecases.catalog_db_use_cases

import com.example.domain.repository.CatalogDBRepository
import javax.inject.Inject

class GetCatalogFromCacheUseCase @Inject constructor(
    private val catalogDBRepository: CatalogDBRepository
) {
    suspend operator fun invoke() = catalogDBRepository.getCatalog()
}