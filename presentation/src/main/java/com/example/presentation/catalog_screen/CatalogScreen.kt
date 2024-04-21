package com.example.presentation.catalog_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.R
import com.example.presentation.common.Button
import com.example.presentation.common.CategoriesRow
import com.example.presentation.common.FilterBottomSheet
import com.example.presentation.common.ItemCard
import com.example.presentation.common.SearchBar
import com.example.presentation.common.TopLine
import com.example.presentation.common.ZeroResultText

@Composable
fun CatalogScreen(
    viewModel: CatalogScreenViewModel,
    navigateToDetail: (id: Int) -> Unit,
    navigateToShoppingCart: () -> Unit,
) {
    val categoriesState =
        viewModel.catalogState.collectAsStateWithLifecycle(CatalogScreenState.Initial)

    CatalogScreenContent(
        categoriesState = categoriesState,
        viewModel = viewModel,
        navigateToDetail = navigateToDetail,
        navigateToShoppingCart = navigateToShoppingCart,
    )
}

@Composable
fun CatalogScreenContent(
    categoriesState: State<CatalogScreenState?>,
    viewModel: CatalogScreenViewModel,
    navigateToDetail: (id: Int) -> Unit,
    navigateToShoppingCart: () -> Unit,
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var isSearch by remember { mutableStateOf(false) }
    var searchQuery by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            if (!isSearch) {
                TopLine(
                    viewModel = viewModel,
                    filterClickAction = { showBottomSheet = true },
                    searchClickAction = { isSearch = true }
                )
            } else {
                SearchBar(
                    searchQuery = searchQuery,
                    newSearchQuery = { newQuery -> searchQuery = newQuery },
                ) {
                    isSearch = false
                }
            }
        },
        containerColor = Color.White
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
        ) {
            when (val currentState = categoriesState.value) {
                is CatalogScreenState.Initial -> {}
                is CatalogScreenState.Loading -> {
                    Box(
                        modifier = Modifier
                            .padding(contentPadding)
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is CatalogScreenState.Error -> {
                    ZeroResultText(text = stringResource(id = R.string.Error_loading_data))
                }

                is CatalogScreenState.CatalogState -> {
                    if (currentState.catalog != null && !isSearch) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                        ) {
                            CategoriesRow(
                                categories = currentState.catalog.categoryList.map { it },
                                viewModel = viewModel
                            )
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            ) {
                                if (viewModel.getFilteredProductList
                                        (currentState.catalog.productList).isNotEmpty()
                                ) {
                                    LazyVerticalGrid(
                                        modifier = Modifier.fillMaxSize(),
                                        columns = GridCells.Fixed(2),
                                        verticalArrangement = Arrangement.spacedBy(
                                            dimensionResource(id = R.dimen.half_padding)),
                                        horizontalArrangement = Arrangement.spacedBy(
                                            dimensionResource(id = R.dimen.half_padding)),
                                    ) {
                                        items(items = viewModel.getFilteredProductList(
                                            currentState.catalog.productList)
                                        ) {
                                            ItemCard(
                                                product = it,
                                                viewModel = viewModel,
                                                navigateToDetail = navigateToDetail,
                                            )
                                        }
                                    }
                                } else {
                                    ZeroResultText(
                                        text = stringResource(id = R.string.Zero_filter)
                                    )
                                }
                            }

                            if (viewModel.shoppingCart.value.isNotEmpty()) {
                                Button(sum = viewModel.getSum().toString()) {
                                    navigateToShoppingCart()
                                }
                            }
                        }
                    } else {
                        if (searchQuery.isNotEmpty() && currentState.catalog != null) {
                            val filteredProductList = viewModel.getSearchProduct(
                                searchQuery,
                                currentState.catalog.productList
                            )

                            if (filteredProductList.isNotEmpty()) {
                                LazyVerticalGrid(
                                    modifier = Modifier.fillMaxSize(),
                                    columns = GridCells.Fixed(2),
                                    verticalArrangement = Arrangement.spacedBy(
                                        dimensionResource(id = R.dimen.half_padding)),
                                    horizontalArrangement = Arrangement.spacedBy(
                                        dimensionResource(id = R.dimen.half_padding)),
                                ) {
                                    items(
                                        items = viewModel.getSearchProduct(
                                            searchQuery,
                                            currentState.catalog.productList)
                                    ) {
                                        ItemCard(
                                            product = it,
                                            viewModel = viewModel,
                                            navigateToDetail = navigateToDetail,
                                        )
                                    }
                                }
                            } else {
                                ZeroResultText(text = stringResource(id = R.string.Zero_filtered_result))
                            }
                        } else {
                            ZeroResultText(text = stringResource(id = R.string.Enter_product_name))
                        }
                    }
                }
                null -> TODO()
            }
            if (showBottomSheet) {
                FilterBottomSheet(viewModel = viewModel) {
                    showBottomSheet = it
                }
            }
        }
    }
}