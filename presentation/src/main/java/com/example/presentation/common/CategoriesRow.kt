package com.example.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.Category
import com.example.presentation.R
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.theme.Dark
import com.example.presentation.theme.Primary

@Composable
fun CategoriesRow(
    categories: List<Category>,
    viewModel: CatalogScreenViewModel,
) {
    LazyRow(
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.main_padding),
            top = dimensionResource(id = R.dimen.half_padding),
            bottom = dimensionResource(id = R.dimen.half_padding)
        ),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.half_padding))
        ) {
        items(categories.size) { category ->
            androidx.compose.material3.Button(
                onClick = {
                    viewModel.changeCategory(categories[category].id)
                },
                colors = ButtonDefaults.buttonColors(
                    if (viewModel.categoryId.value == categories[category].id) Primary else Color.White
                ),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.half_padding)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
            ) {
                Text(
                    text = categories[category].name,
                    color =
                    if (viewModel.categoryId.value == categories[category].id) Color.White
                    else Dark,
                    fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}