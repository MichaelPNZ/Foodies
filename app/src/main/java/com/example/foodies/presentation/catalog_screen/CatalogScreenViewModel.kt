package com.example.foodies.presentation.catalog_screen

import androidx.lifecycle.ViewModel
import com.example.foodies.domain.usecases.GetCatalogUseCase
import com.example.foodies.domain.usecases.GetCategoriesUseCase
import com.example.foodies.domain.usecases.GetProductsUseCase
import com.example.foodies.utils.LoadResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class CatalogScreenViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val getCatalogUseCase: GetCatalogUseCase,
) : ViewModel() {

    fun catalogState(): Flow<CatalogScreenState> {
        return getCatalogUseCase()
            .map { result ->
                when (result) {
                    is LoadResource.Success -> {
                        CatalogScreenState.CatalogState(catalog = result.data)
                    }
                    is LoadResource.Error -> {
                        CatalogScreenState.Error
                    }
                    is LoadResource.Loading -> {
                        CatalogScreenState.Loading
                    }

                    null -> TODO()
                }
            }
            .onStart { emit(CatalogScreenState.Loading) }
    }
}