package com.example.presentation.shopping_cart_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.common.CartRow
import com.example.presentation.common.Header
import com.example.presentation.common.ZeroResultText
import com.example.presentation.theme.Primary
import com.example.utils.Constants

@Composable
fun ShoppingCartScreen(
    viewModel: CatalogScreenViewModel,
    navigateToDetail: (id: Int) -> Unit,
    navigateBack: () -> Unit,
) {
    val shoppingCartState by remember { viewModel.shoppingCart }

    Scaffold(
        topBar = {
            Header(title = "Корзина") {
                navigateBack()
            }
        },
        containerColor = Color.White
    ) { contentPadding ->
        if (shoppingCartState.isNotEmpty()) {
            Column {
                LazyColumn(
                    modifier = Modifier
                        .padding(contentPadding)
                        .weight(1f)
                        .fillMaxWidth(),
                ) {
                    items(shoppingCartState.size) { index ->
                        CartRow(
                            product = shoppingCartState[index].product,
                            viewModel = viewModel
                        ) {
                            navigateToDetail(shoppingCartState[index].product.id)
                        }
                        HorizontalDivider()
                    }
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonColors(
                        containerColor = Primary,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White
                    ),
                    onClick = {
                        viewModel.makeOrder()
                        navigateBack()
                    }
                ) {
                    Text(
                        text = "Заказать за ${viewModel.getSum()} ${Constants.RUR}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        } else {
            ZeroResultText(
                text = "Пусто, выберите блюда\n" +
                        "в каталоге :)"
            )
        }
    }
}