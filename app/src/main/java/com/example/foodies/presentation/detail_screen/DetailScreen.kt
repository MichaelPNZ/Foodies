package com.example.foodies.presentation.detail_screen

import android.util.Log
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.Product
import com.example.foodies.R
import com.example.foodies.presentation.catalog_screen.CatalogScreenViewModel
import com.example.foodies.presentation.common.Counter
import com.example.foodies.presentation.common.ListItem
import com.example.foodies.presentation.theme.Dark
import com.example.foodies.presentation.theme.Primary

@Composable
fun DetailScreen(
    product: Product,
    viewModel: CatalogScreenViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
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
                text = product.name,
                fontSize = 34.sp,
                lineHeight = 36.sp,
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .alpha(0.6f),
                text = product.description,
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
                measure = product.measure.toString(),
                unit = product.measureUnit
            )
            ListItem(
                category = "Энерг. ценность",
                measure = product.energyPer100Grams.toString(),
                unit = "ккал"
            )
            ListItem(
                category = "Белки",
                measure = product.proteinsPer100Grams.toString(),
                unit = product.measureUnit
            )
            ListItem(
                category = "Жиры",
                measure = product.fatsPer100Grams.toString(),
                unit = product.measureUnit
            )
            ListItem(
                category = "Углеводы",
                measure = product.carbohydratesPer100Grams.toString(),
                unit = product.measureUnit
            )
        }
        if (viewModel.shoppingCart.value.find { it.product == product } != null) {
            Counter(
                product = product,
                viewModel = viewModel
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .background(Primary, RoundedCornerShape(8.dp))
                    .clickable {
                        viewModel.addToShoppingCart(product)
                        Log.i("!!!", "${viewModel.shoppingCart.value}")
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier.size(24.dp)
                            .padding(end = 8.dp),
                        painter = painterResource(id = R.drawable.cart),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.White)
                    )

                    Text(
                        text = "${product.priceCurrent} ${com.example.utils.Constants.RUR}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                }
            }
        }
    }
}