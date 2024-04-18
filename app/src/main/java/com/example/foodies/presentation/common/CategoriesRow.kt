package com.example.foodies.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.Category
import com.example.foodies.presentation.catalog_screen.CatalogScreenViewModel
import com.example.foodies.presentation.theme.Dark
import com.example.foodies.presentation.theme.Primary

@Composable
fun CategoriesRow(
    categories: List<Category>,
    viewModel: CatalogScreenViewModel,
) {
    LazyRow(
        modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),) {
        items(categories.size) { category ->
            Button(
                onClick = {
                    viewModel.changeCategory(categories[category].id)
                },
                colors = ButtonDefaults.buttonColors(
                    if (viewModel.categoryId.value == categories[category].id) Primary else Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
            ) {
                Text(
                    text = categories[category].name,
                    color =
                    if (viewModel.categoryId.value == categories[category].id) Color.White
                    else Dark,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}