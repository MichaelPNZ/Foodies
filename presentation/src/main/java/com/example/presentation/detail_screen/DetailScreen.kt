package com.example.presentation.detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.common.CounterBig
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
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_24))
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
                verticalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.half_padding)
                )
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(id = R.dimen.main_padding)),
                    text = selectedProduct!!.name,
                    fontSize = dimensionResource(id = R.dimen.font_size_34).value.sp,
                    lineHeight = dimensionResource(id = R.dimen.font_size_36).value.sp,
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.main_padding))
                        .alpha(0.6f),
                    text = selectedProduct!!.description,
                    fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                    lineHeight = dimensionResource(id = R.dimen.font_size_24).value.sp,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                HorizontalDivider()
                ListItem(
                    category = stringResource(id = R.string.Weight),
                    measure = selectedProduct!!.measure.toString(),
                    unit = selectedProduct!!.measureUnit
                )
                ListItem(
                    category = stringResource(id = R.string.Energy_value),
                    measure = selectedProduct!!.energyPer100Grams.toString(),
                    unit = stringResource(id = R.string.kcal)
                )
                ListItem(
                    category = stringResource(id = R.string.protein),
                    measure = selectedProduct!!.proteinsPer100Grams.toString(),
                    unit = selectedProduct!!.measureUnit
                )
                ListItem(
                    category = stringResource(id = R.string.Fat),
                    measure = selectedProduct!!.fatsPer100Grams.toString(),
                    unit = selectedProduct!!.measureUnit
                )
                ListItem(
                    category = stringResource(id = R.string.Carbohydrates),
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
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.main_padding),
                            vertical = dimensionResource(id = R.dimen.padding_12)
                        ),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.half_padding)),
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
                        text = "${stringResource(id = R.string.Add_to_cart_for)} ${selectedProduct!!.priceCurrent} ${Constants.RUR}",
                        fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
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