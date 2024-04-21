package com.example.presentation.common

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.Product
import com.example.presentation.R
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.theme.GrayBg

@Composable
fun ItemCard(
    product: Product,
    viewModel: CatalogScreenViewModel,
    navigateToDetail: (id: Int) -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = GrayBg),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clickable { navigateToDetail(product.id) }
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.mock_photo),
                contentDescription = null,
            )

            Icon(
                painter = painterResource(id = R.drawable.favorite_icon),
                contentDescription = null,
                modifier = Modifier
                    .padding(
                        end = dimensionResource(id = R.dimen.half_padding),
                        top = dimensionResource(id = R.dimen.half_padding)
                    )
                    .align(Alignment.TopEnd)
                    .clickable {
                        viewModel.toggleFavorite(product)
                    },
                tint = if (viewModel.isFavoriteChecked(product)) Color.Red else Color.LightGray
            )

            if (product.tagIds.isNotEmpty()) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.padding_24))
                        .padding(
                            start = dimensionResource(id = R.dimen.half_padding),
                            top = dimensionResource(id = R.dimen.half_padding)
                        ),
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
                .padding(
                    start = dimensionResource(id = R.dimen.padding_12),
                    end = dimensionResource(id = R.dimen.padding_12),
                    bottom = dimensionResource(id = R.dimen.padding_12)
                ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_12))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_4))
            ) {
                Text(
                    text = product.name,
                    fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = dimensionResource(id = R.dimen.font_size_16).value.sp
                )
                Text(
                    modifier = Modifier.alpha(0.6f),
                    text = "${product.measure} ${product.measureUnit}",
                    fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = dimensionResource(id = R.dimen.font_size_20).value.sp
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