package com.example.foodies.presentation.catalog_screen

import com.example.foodies.domain.model.Catalog

sealed class CatalogScreenState {

    data object Initial : CatalogScreenState()

    data object Loading : CatalogScreenState()

    data object Error : CatalogScreenState()

    data class CatalogState(
        val catalog: Catalog?
    ) : CatalogScreenState()
}