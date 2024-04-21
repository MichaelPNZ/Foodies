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
import com.example.domain.usecases.catalog_db_use_cases.GetCatalogFromCacheUseCase
import com.example.domain.usecases.catalog_db_use_cases.InsertCatalogToCache
import com.example.domain.usecases.catalog_use_cases.GetCatalogUseCase
import com.example.domain.usecases.user_db_use_case.GetIsLoginUserUseCase
import com.example.domain.usecases.user_db_use_case.SaveUserUseCase
import com.example.utils.LoadResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogScreenViewModel @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val getCatalogFromCacheUseCase: GetCatalogFromCacheUseCase,
    private val insertCatalogToCache: InsertCatalogToCache,
    private val getIsLoginUserUseCase: GetIsLoginUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
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

    private val _favoriteList: MutableState<List<Product>> =
        mutableStateOf(emptyList())
    val favoriteList: State<List<Product>> = _favoriteList

    init {
        viewModelScope.launch {
            val cachedCatalog = getCatalogFromCacheUseCase()
            cachedCatalog?.let {
                _categoryId.intValue = it.categoryList.firstOrNull()?.id ?: 0
                _tagList.value = it.tagList
            }

            val user = getIsLoginUserUseCase()
            _favoriteList.value = user?.favoriteProductList ?: emptyList()
        }
    }

    val catalogState = getCatalogUseCase()
        .map { result ->
            when (result) {
                is LoadResource.Success -> {
                    result.data?.let { catalog ->
                        insertCatalogToCache(catalog)
                        _categoryId.intValue = catalog.categoryList.firstOrNull()?.id ?: 0
                        _tagList.value = catalog.tagList
                        CatalogScreenState.CatalogState(catalog)
                    }
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
        val currentProduct = _shoppingCart.value.find { it.product.id == product.id}

        val shoppingCartLastId = _shoppingCart.value.lastOrNull()?.id ?: 0
        val newId = shoppingCartLastId + 1
        val newShoppingCart = ShoppingCart(newId, product, 1)

        if (currentProduct == null) {
            _shoppingCart.value += newShoppingCart
        } else {
            _shoppingCart.value = _shoppingCart.value.map {
                if (it.product == product) it.copy(count = currentProduct.count + 1)
                else it
            }
        }
    }

    fun deleteFromShoppingCart(product: Product) {
        val currentProduct = _shoppingCart.value.find { it.product.id == product.id}

        if (currentProduct != null && currentProduct.count > 1) {
            _shoppingCart.value = _shoppingCart.value.map {
                if (it.product == product) it.copy(count = currentProduct.count - 1)
                else it
            }
        } else if (currentProduct != null && currentProduct.count >= 1) {
            _shoppingCart.value = _shoppingCart.value.minus(ShoppingCart(currentProduct.id,product, 1))
        }
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

    fun makeOrder() {
        viewModelScope.launch {
            val user = getIsLoginUserUseCase()
            val currentCartList = _shoppingCart.value
            if (user != null) {
                val updatedShoppingCartList = user.copy(shoppingCartList = user.shoppingCartList + listOf(currentCartList))
                saveUserUseCase(updatedShoppingCartList)
                _shoppingCart.value = emptyList()
            }
        }
    }

    fun getSearchProduct(newQuery: String, currentProductList: List<Product>) : List<Product> {
        return currentProductList.filter { it.name.equals(newQuery, ignoreCase = true) }
    }

    fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            val user = getIsLoginUserUseCase()
            if (_favoriteList.value.contains(product) && user != null) {
                val updatedFavoriteList = user.copy(favoriteProductList = user.favoriteProductList - listOf(product).toSet())
                saveUserUseCase(updatedFavoriteList)
                _favoriteList.value = updatedFavoriteList.favoriteProductList
            } else if (!_favoriteList.value.contains(product) && user != null) {
                val updatedFavoriteList = user.copy(favoriteProductList = user.favoriteProductList + listOf(product).toSet())
                saveUserUseCase(updatedFavoriteList)
                _favoriteList.value = updatedFavoriteList.favoriteProductList
            }
        }
    }

    fun isFavoriteChecked(product: Product): Boolean {
       return _favoriteList.value.contains(product)
    }
}