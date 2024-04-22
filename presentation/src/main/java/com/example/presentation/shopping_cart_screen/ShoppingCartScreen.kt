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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.presentation.R
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
            Header(title = stringResource(id = R.string.cart)) {
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
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.main_padding),
                            vertical = dimensionResource(id = R.dimen.padding_12)
                        ),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.half_padding)),
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
                        text = "${stringResource(id = R.string.order_for)} ${viewModel.getSum()} ${Constants.RUR}",
                        fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        } else {
            ZeroResultText(
                text = stringResource(id = R.string.zero_selected_product)
            )
        }
    }
}