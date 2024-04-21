package com.example.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.domain.model.Product
import com.example.presentation.R
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.theme.Dark
import com.example.utils.Constants

@Composable
fun AddToCartButton(
    product: Product,
    viewModel: CatalogScreenViewModel,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(dimensionResource(id = R.dimen.half_padding)))
            .padding(dimensionResource(id = R.dimen.padding_12))
            .clickable {
                viewModel.addToShoppingCart(product)
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.half_padding)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${product.priceCurrent} ${Constants.RUR}",
                color = Dark,
                fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                fontWeight = FontWeight.SemiBold
            )
            if (product.priceOld != 0) {
                Text(
                    modifier = Modifier.alpha(0.6f),
                    text = "${product.priceOld} ${Constants.RUR}",
                    color = Dark,
                    fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )
            }
        }
    }
}