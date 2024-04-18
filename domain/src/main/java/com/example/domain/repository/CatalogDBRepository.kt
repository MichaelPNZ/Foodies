package com.example.domain.repository

import com.example.domain.model.Catalog

interface CatalogDBRepository {

    suspend fun getCatalog(): Catalog?
    suspend fun insertCatalog(catalog: Catalog)
}