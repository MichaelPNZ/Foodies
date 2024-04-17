package com.example.foodies.presentation.common

import android.util.Log
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.foodies.R
import com.example.foodies.domain.model.Product
import com.example.foodies.presentation.catalog_screen.CatalogScreenViewModel
import com.example.foodies.presentation.theme.Primary

@Composable
fun Counter(
    product: Product,
    viewModel: CatalogScreenViewModel,
) {
    Row(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier.background(Color.White, RoundedCornerShape(8.dp))
        ) {
            Image(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        viewModel.deleteFromShoppingCart(product)
                        Log.i("!!!", "${viewModel.shoppingCart.value}")
                    },
                painter = painterResource(id = R.drawable.minus),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Primary)
            )
        }

        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .align(Alignment.CenterVertically)
                .weight(1f),
            text = viewModel.getProductCount(product).toString(),
            textAlign = TextAlign.Center,
            color = Color.Black,
        )

        Box(
            modifier = Modifier.background(Color.White, RoundedCornerShape(8.dp))
        ) {
            Image(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        viewModel.addToShoppingCart(product)
                        Log.i("!!!", "${viewModel.shoppingCart.value}")

                    },
                painter = painterResource(id = R.drawable.plus),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Primary)
            )
        }
    }
}