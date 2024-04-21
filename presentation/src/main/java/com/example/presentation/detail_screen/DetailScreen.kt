package com.example.presentation.detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.Product
import com.example.presentation.R
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.common.ListItem
import com.example.presentation.theme.Dark
import com.example.presentation.theme.Primary
import com.example.utils.Constants

@Composable
fun DetailScreen(
    id: Int,
    viewModel: CatalogScreenViewModel,
    navigateBack: () -> Unit,
) {
    viewModel.getProduct(id)
    val selectedProduct by remember { viewModel.selectedProduct }

    if (selectedProduct != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = R.drawable.mock_photo),
                    contentDescription = null,
                )
                IconButton(
                    onClick = { navigateBack() }) {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_left),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Dark)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = selectedProduct!!.name,
                    fontSize = 34.sp,
                    lineHeight = 36.sp,
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .alpha(0.6f),
                    text = selectedProduct!!.description,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                HorizontalDivider()
                ListItem(
                    category = "Вес",
                    measure = selectedProduct!!.measure.toString(),
                    unit = selectedProduct!!.measureUnit
                )
                ListItem(
                    category = "Энерг. ценность",
                    measure = selectedProduct!!.energyPer100Grams.toString(),
                    unit = "ккал"
                )
                ListItem(
                    category = "Белки",
                    measure = selectedProduct!!.proteinsPer100Grams.toString(),
                    unit = selectedProduct!!.measureUnit
                )
                ListItem(
                    category = "Жиры",
                    measure = selectedProduct!!.fatsPer100Grams.toString(),
                    unit = selectedProduct!!.measureUnit
                )
                ListItem(
                    category = "Углеводы",
                    measure = selectedProduct!!.carbohydratesPer100Grams.toString(),
                    unit = selectedProduct!!.measureUnit
                )
            }
            if (viewModel.shoppingCart.value.find { it.product == selectedProduct } != null) {
                CounterBig(
                    product = selectedProduct!!,
                    viewModel = viewModel
                )
            } else {
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
                        viewModel.addToShoppingCart(selectedProduct!!)
                    }
                ) {
                    Text(
                        text = "В корзину за ${selectedProduct!!.priceCurrent} ${Constants.RUR}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
            ,
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun CounterBig(
    product: Product,
    viewModel: CatalogScreenViewModel,
) {
    Row(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(
            modifier = Modifier.height(50.dp)
                .aspectRatio(1f)
                .background(Color.White, RoundedCornerShape(8.dp)
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
                .padding(horizontal = 12.dp)
                .align(Alignment.CenterVertically)
                .weight(1f),
            text = viewModel.getProductCount(product).toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color.Black,
        )

        Box(
            modifier = Modifier.height(50.dp)
                .aspectRatio(1f)
                .background(Color.White, RoundedCornerShape(8.dp))
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