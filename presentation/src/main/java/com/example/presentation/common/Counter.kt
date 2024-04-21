package com.example.presentation.common

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.domain.model.Product
import com.example.presentation.R
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.theme.Primary

@Composable
fun Counter(
    product: Product,
    viewModel: CatalogScreenViewModel,
) {
    Row(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_12)),
    ) {
        Box(
            modifier = Modifier.background(Color.White, RoundedCornerShape(
                dimensionResource(id = R.dimen.half_padding))
            )
        ) {
            Image(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_12))
                    .clickable {
                        viewModel.deleteFromShoppingCart(product)
                    },
                painter = painterResource(id = R.drawable.minus),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Primary)
            )
        }

        Text(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_12))
                .align(Alignment.CenterVertically)
                .weight(1f),
            text = viewModel.getProductCount(product).toString(),
            textAlign = TextAlign.Center,
            color = Color.Black,
        )

        Box(
            modifier = Modifier.background(Color.White, RoundedCornerShape(
                dimensionResource(id = R.dimen.half_padding))
            )
        ) {
            Image(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_12))
                    .clickable {
                        viewModel.addToShoppingCart(product)
                    },
                painter = painterResource(id = R.drawable.plus),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Primary)
            )
        }
    }
}