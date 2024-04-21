package com.example.presentation.personal_account_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.presentation.common.Header
import com.example.presentation.common.OrderItem

@Composable
fun OrderDetailScreen(
    shoppingCartIndex: Int,
    viewModel: PersonalAccountViewModel,
    navigateBack: () -> Unit
) {
    val shoppingCartList = viewModel.getShoppingList(shoppingCartIndex)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Header(title = "Список заказа") {
            navigateBack()
        }

        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            items(shoppingCartList.size) { index ->
                OrderItem(index = index, shoppingCartList = shoppingCartList)
                HorizontalDivider()
            }
        }
    }
}