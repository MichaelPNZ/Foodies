package com.example.presentation.catalog_screen

import com.example.domain.model.Catalog


sealed class CatalogScreenState {

    data object Initial : CatalogScreenState()
    data object Loading : CatalogScreenState()
    data object Error : CatalogScreenState()

    data class CatalogState(
        val catalog: Catalog?
    ) : CatalogScreenState()
}