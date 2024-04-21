package com.example.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.ShoppingCart
import com.example.presentation.R
import com.example.presentation.theme.Dark

@Composable
fun OrderItem(
    index: Int,
    shoppingCartList: List<ShoppingCart>,
) {
    Row(
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.main_padding)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.mock_photo),
                contentDescription = null,
            )
            if (shoppingCartList[index].product.tagIds.isNotEmpty()) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.padding_24))
                        .padding(
                            start = dimensionResource(id = R.dimen.half_padding),
                            top = dimensionResource(id = R.dimen.half_padding)
                        ),
                    painter = painterResource(
                        id = when (shoppingCartList[index].product.tagIds) {
                            listOf(2) -> R.drawable.type_without_meat
                            listOf(4) -> R.drawable.type_spicy
                            else -> R.drawable.type_sale
                        }
                    ),
                    contentDescription = null,
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = shoppingCartList[index].product.name,
                color = Dark,
                fontSize = dimensionResource(id = R.dimen.font_size_14).value.sp,
                lineHeight = dimensionResource(id = R.dimen.font_size_20).value.sp
            )

            Text(
                text = "Кол-во: ${shoppingCartList[index].count} шт.",
                color = Dark,
                fontSize = dimensionResource(id = R.dimen.font_size_14).value.sp,
                lineHeight = dimensionResource(id = R.dimen.font_size_20).value.sp
            )

            Text(
                text = "Сумма: ${shoppingCartList[index].product.priceCurrent * shoppingCartList[index].count} ₽",
                color = Dark,
                fontSize = dimensionResource(id = R.dimen.font_size_14).value.sp,
                lineHeight = dimensionResource(id = R.dimen.font_size_20).value.sp
            )
        }
    }
}