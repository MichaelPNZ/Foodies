package com.example.presentation.catalog_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Product
import com.example.domain.model.ShoppingCart
import com.example.domain.model.Tag
import com.example.domain.usecases.GetCatalogFromCacheUseCase
import com.example.domain.usecases.GetCatalogUseCase
import com.example.domain.usecases.InsertCatalogToCache
import com.example.utils.LoadResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogScreenViewModel @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val getCatalogFromCacheUseCase: GetCatalogFromCacheUseCase,
    private val insertCatalogToCache: InsertCatalogToCache,
) : ViewModel() {

    private val _categoryId = mutableIntStateOf(0)
    val categoryId: State<Int> = _categoryId

    private val _shoppingCart = mutableStateOf(emptyList<ShoppingCart>())
    val shoppingCart: State<List<ShoppingCart>> = _shoppingCart

    private val _tagList: MutableState<List<Tag>> =
        mutableStateOf(emptyList())
    val tagList: State<List<Tag>> = _tagList

    private val _selectedTagList: MutableState<List<Tag>> =
        mutableStateOf(emptyList())
    val selectedTagList: State<List<Tag>> = _selectedTagList

    private val _selectedProduct = mutableStateOf<Product?>(null)
    val selectedProduct: State<Product?> = _selectedProduct

    fun catalogState(): Flow<CatalogScreenState> {
        return getCatalogUseCase()
            .map { result ->
                when (result) {
                    is LoadResource.Success -> {
                        result.data?.let { insertCatalogToCache(it) }
                        _categoryId.intValue = result.data?.categoryList?.first()?.id ?:0
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

    fun getFilteredProductList(currentProductList: List<Product>) : List<Product> {
        return if (_selectedTagList.value.isEmpty()) {
            currentProductList.filter { it.categoryId == _categoryId.intValue }
        } else {
            currentProductList.filter { it.categoryId == _categoryId.intValue }
                .filter { product -> product.tagIds.containsAll(_selectedTagList.value.map { it.id }) }
        }
    }

    fun changeCategory(id: Int) {
        _categoryId.intValue = id
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
    }

    fun getProductCount(product: Product): Int {
        return _shoppingCart.value.find { it.product.id == product.id }?.count ?: 0
    }

    fun getSum(): Int {
        return _shoppingCart.value.sumOf { it.product.priceCurrent * it.count }
    }

    fun checkedSelectedTagList(tag: Tag) {
        if (_selectedTagList.value.contains(tag)) {
            _selectedTagList.value -= tag
        } else {
            _selectedTagList.value += tag
        }
    }

    fun getProduct(id: Int) {
        viewModelScope.launch {
            val catalog = getCatalogFromCacheUseCase()
            if (catalog != null) {
                _selectedProduct.value = catalog.productList.find { it.id == id }
            }
        }
    }
}