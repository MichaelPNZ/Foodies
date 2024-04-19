package com.example.foodies.data.repository

import com.example.domain.model.Catalog
import com.example.domain.repository.CatalogDBRepository
import com.example.foodies.data.local.dao.CatalogDao
import com.example.foodies.data.local.entity.CatalogDBO
import com.example.foodies.data.mapper.toCatalog
import javax.inject.Inject

class CatalogDBRepositoryImpl @Inject constructor(
    private val catalogDao: CatalogDao
) : CatalogDBRepository {

    override suspend fun getCatalog(): Catalog? {
        return  catalogDao.getCatalog()?.toCatalog()
    }

    override suspend fun insertCatalog(catalog: Catalog) {
        catalogDao.insertCatalog(
            CatalogDBO(
                catalog.categoryList,
                catalog.productList,
                catalog.tagList
            ))
    }
}