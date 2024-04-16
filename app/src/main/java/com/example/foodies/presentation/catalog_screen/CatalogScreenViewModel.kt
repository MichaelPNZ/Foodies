package com.example.foodies.presentation.catalog_screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.foodies.domain.model.Product
import com.example.foodies.domain.model.ShoppingCart
import com.example.foodies.domain.model.Tag
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

    private val _categoryId: MutableState<Int> =
        mutableIntStateOf(0)
    val categoryId: State<Int> = _categoryId

    private val _shoppingCart: MutableState<List<ShoppingCart>> =
        mutableStateOf(emptyList())
    val shoppingCart: State<List<ShoppingCart>> = _shoppingCart

    private val _tagList: MutableState<List<Tag>> =
        mutableStateOf(emptyList())
    val tagList: State<List<Tag>> = _tagList

    fun catalogState(): Flow<CatalogScreenState> {
        return getCatalogUseCase()
            .map { result ->
                when (result) {
                    is LoadResource.Success -> {
                        _categoryId.value = result.data?.categoryList?.first()?.id ?:0
                        _tagList.value = result.data?.tagList ?: emptyList()
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

    fun changeCategory(id: Int) {
        _categoryId.value = id
    }

    fun addToShoppingCart(product: Product) {
        val updatedShoppingCart = if (_shoppingCart.value.any { it.product.id == product.id }) {
            _shoppingCart.value.map {
                if (it.product.id == product.id) {
                    it.copy(count = it.count + 1)
                } else {
                    it
                }
            }
        } else {
            _shoppingCart.value + ShoppingCart(product, 1)
        }
        _shoppingCart.value = updatedShoppingCart
        Log.i("!!!", "$_shoppingCart")
    }

    fun deleteFromShoppingCart(product: Product) {
        val updatedShoppingCart = if (_shoppingCart.value.any { it.product.id == product.id && it.count > 1 }) {
            _shoppingCart.value.map {
                if (it.product.id == product.id) {
                    it.copy(count = it.count - 1)
                } else {
                    it
                }
            }
        } else {
            _shoppingCart.value - ShoppingCart(product, 1)
        }
        _shoppingCart.value = updatedShoppingCart
        Log.i("!!!", "$_shoppingCart")
    }

    fun getProductCount(product: Product): Int {
        return _shoppingCart.value.find { it.product.id == product.id }?.count ?: 0
    }

    fun getSum(): Int {
        return _shoppingCart.value.sumOf { it.product.priceCurrent * it.count }
    }
}