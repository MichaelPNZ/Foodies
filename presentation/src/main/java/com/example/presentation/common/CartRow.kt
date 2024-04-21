package com.example.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.Product
import com.example.presentation.R
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.theme.Dark
import com.example.presentation.theme.GrayBg
import com.example.presentation.theme.Primary

@Composable
fun CartRow(
    product: Product,
    viewModel: CatalogScreenViewModel,
    navigateToDetail: (id: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .height(130.dp)
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.main_padding))
            .background(Color.White)
            .clickable { navigateToDetail(product.id) },
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

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_12))
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = product.name,
                color = Dark,
                maxLines = 2,
                fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                lineHeight = dimensionResource(id = R.dimen.font_size_20).value.sp
            )

            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.main_padding)
                )
            ) {
                Row(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .height(46.dp),
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(id = R.dimen.padding_12)
                    ),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .background(
                                GrayBg, RoundedCornerShape(
                                    dimensionResource(id = R.dimen.padding_12)
                                )
                            )
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.half_padding))
                                .fillMaxSize()
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
                            .align(Alignment.CenterVertically),
                        text = viewModel.getProductCount(product).toString(),
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .background(
                                GrayBg, RoundedCornerShape(
                                    dimensionResource(id = R.dimen.half_padding)
                                )
                            )
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.half_padding))
                                .fillMaxSize()
                                .clickable {
                                    viewModel.addToShoppingCart(product)
                                },
                            painter = painterResource(id = R.drawable.plus),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Primary)
                        )
                    }
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = product.priceCurrent.toString(),
                        fontWeight = FontWeight.Medium,
                        fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                        lineHeight = dimensionResource(id = R.dimen.font_size_24).value.sp,
                        textAlign = TextAlign.End
                    )
                    if (product.priceOld != 0) {
                        Text(
                            modifier = Modifier.alpha(0.6f),
                            text = product.priceOld.toString(),
                            fontSize = dimensionResource(id = R.dimen.font_size_14).value.sp,
                            lineHeight = dimensionResource(id = R.dimen.font_size_20).value.sp,
                            style = TextStyle(textDecoration = TextDecoration.LineThrough)
                        )
                    }
                }
            }
        }
    }
}