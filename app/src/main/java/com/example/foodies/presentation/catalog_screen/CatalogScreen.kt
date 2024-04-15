package com.example.foodies.presentation.catalog_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.foodies.presentation.common.CategoriesRow
import com.example.foodies.presentation.common.ItemCard

@Preview
@Composable
fun CatalogScreen(
    viewModel: CatalogScreenViewModel = hiltViewModel()
) {
    val categoriesState =
        viewModel.catalogState().collectAsStateWithLifecycle(CatalogScreenState.Initial)

    CatalogScreenContent(
        categoriesState = categoriesState,
        viewModel = viewModel
    )
}

@Composable
fun CatalogScreenContent(
    categoriesState: State<CatalogScreenState>,
    viewModel: CatalogScreenViewModel,
) {

    when (val currentState = categoriesState.value) {
        is CatalogScreenState.Initial -> {}
        is CatalogScreenState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is CatalogScreenState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error")
            }
        }

        is CatalogScreenState.CatalogState -> {
            Column {
                Spacer(modifier = Modifier.size(8.dp))

                CategoriesRow(
                    categories = currentState.catalog?.categoryList?.map { it } ?: listOf())

                Spacer(modifier = Modifier.size(8.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(items = currentState.catalog?.productList ?: listOf()) {
                        ItemCard(
                            product = it
                        )
                    }
                }
            }
        }
    }
}