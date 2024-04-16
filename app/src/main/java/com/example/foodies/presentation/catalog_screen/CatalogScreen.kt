package com.example.foodies.presentation.catalog_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.foodies.presentation.common.Button
import com.example.foodies.presentation.common.CategoriesRow
import com.example.foodies.presentation.common.FilterItem
import com.example.foodies.presentation.common.ItemCard
import com.example.foodies.presentation.common.TopLine
import com.example.foodies.presentation.theme.Primary
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreenContent(
    categoriesState: State<CatalogScreenState>,
    viewModel: CatalogScreenViewModel,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopLine {
                showBottomSheet = true
            }
        }
    ) { contentPadding ->
        when (val currentState = categoriesState.value) {
            is CatalogScreenState.Initial -> {}
            is CatalogScreenState.Loading -> {
                Box(
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize()
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is CatalogScreenState.Error -> {
                Box(
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize()
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Error loading data")
                }
            }

            is CatalogScreenState.CatalogState -> {
                Column(
                    modifier = Modifier
                        .padding(contentPadding)
                        .fillMaxSize()
                    ,
                ) {
                    CategoriesRow(
                        categories = currentState.catalog?.categoryList?.map { it } ?: listOf(),
                        viewModel = viewModel
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(),
                            columns = GridCells.Fixed(2),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(items = currentState.catalog?.productList?.filter {
                                it.categoryId == viewModel.categoryId.value
                            } ?: listOf()) {
                                ItemCard(
                                    product = it,
                                    viewModel = viewModel
                                )
                            }
                        }
                    }

                    if (viewModel.shoppingCart.value.isNotEmpty()) {
                        Button(
                            sum = viewModel.getSum().toString()
                        )
                    }
                }
            }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                containerColor = Color.White,
            ) {
                
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 24.dp, bottom = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),

                ) {
                    item {
                        Text(
                            text = "Подобрать блюда",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 24.sp
                        )
                    }

                    items(viewModel.tagList.value.size) { tag ->
                        FilterItem(filterName = viewModel.tagList.value[tag].name)
                    }

                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Primary, RoundedCornerShape(8.dp))
                                .clickable {
                                    scope
                                        .launch { sheetState.hide() }
                                        .invokeOnCompletion {
                                            if (!sheetState.isVisible) {
                                                showBottomSheet = false
                                            }
                                        }
                                }
                        ) {
                            Text(
                                modifier = Modifier.padding(16.dp),
                                text = "Готово",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                            )
                        }
                    }
                }
            }
        }
    }
}