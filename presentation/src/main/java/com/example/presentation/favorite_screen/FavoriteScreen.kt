package com.example.presentation.favorite_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.common.Header
import com.example.presentation.common.ItemCard
import com.example.presentation.common.ZeroResultText

@Composable
fun FavoriteScreen(
    viewModel: CatalogScreenViewModel,
    navigateToDetail: (foodId: Int) -> Unit,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(title = "Избранное") {
            navigateBack()
        }
        if (viewModel.favoriteList.value.isNotEmpty()) {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(items = viewModel.favoriteList.value) {
                    ItemCard(
                        product = it,
                        viewModel = viewModel,
                        navigateToDetail = navigateToDetail,
                    )
                }
            }
        } else {
            ZeroResultText(
                text = "У вас нет избранных блюд :("
            )
        }
    }
}