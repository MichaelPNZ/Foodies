package com.example.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.domain.model.Product
import com.example.presentation.R
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.theme.Primary

@Composable
fun CounterBig(
    product: Product,
    viewModel: CatalogScreenViewModel,
) {
    Row(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.main_padding),
                vertical = dimensionResource(id = R.dimen.padding_12)
            ),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_12)),
    ) {
        Box(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.padding_50))
                .aspectRatio(1f)
                .background(
                    Color.White, RoundedCornerShape(
                        dimensionResource(id = R.dimen.half_padding)
                    )
                )
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        viewModel.deleteFromShoppingCart(product)
                    },
                painter = painterResource(id = R.drawable.minus),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Primary)
            )
        }

        Text(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.padding_12))
                .align(Alignment.CenterVertically)
                .weight(1f),
            text = viewModel.getProductCount(product).toString(),
            fontSize = dimensionResource(id = R.dimen.font_size_18).value.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color.Black,
        )

        Box(
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.padding_50))
                .aspectRatio(1f)
                .background(
                    Color.White, RoundedCornerShape(
                        dimensionResource(id = R.dimen.half_padding)
                    )
                )
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        viewModel.addToShoppingCart(product)
                    },
                painter = painterResource(id = R.drawable.plus),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Primary)
            )
        }
    }
}