package com.example.foodies.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodies.R
import com.example.foodies.domain.model.Product
import com.example.foodies.presentation.catalog_screen.CatalogScreenViewModel
import com.example.foodies.presentation.theme.GrayBg

@Composable
fun ItemCard(
    product: Product,
    viewModel: CatalogScreenViewModel,
    navigateToDetail: (Product) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = GrayBg),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clickable { navigateToDetail(product) }
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.mock_photo),
                contentDescription = null,
            )
            if (product.tagIds.isNotEmpty()) {
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 8.dp, top = 8.dp),
                    painter = painterResource(
                        id = when (product.tagIds) {
                            listOf(2) -> R.drawable.type_without_meat
                            listOf(4) -> R.drawable.type_spicy
                            else -> R.drawable.type_sale
                        }
                    ),
                    contentDescription = null,
                )
            }
        }

        Spacer(modifier = Modifier.size(12.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = product.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp
                )
                Text(
                    modifier = Modifier.alpha(0.6f),
                    text = "${product.measure} ${product.measureUnit}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp
                )
            }
            if (viewModel.shoppingCart.value.find { it.product == product } != null) {
                Counter(
                    product = product,
                    viewModel = viewModel
                )
            } else {
                AddToCartButton(
                    product = product,
                    viewModel = viewModel
                )
            }
        }
    }
}