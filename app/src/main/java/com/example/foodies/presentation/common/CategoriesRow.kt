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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodies.presentation.theme.Dark
import com.example.foodies.presentation.theme.Primary

@Composable
@Preview(showBackground = true)
fun ButtonPreview() {
    CategoriesRow(
        categories = listOf("Category 1", "Category 2", "Category 3")
    )
}

@Composable
fun CategoriesRow(
    categories: List<String>,
) {
    val isSelected = remember { mutableStateOf(true) }

    LazyRow(
        modifier = Modifier.padding(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),) {
        items(categories.size) { category ->
            Button(
                onClick = {
                          isSelected.value = !isSelected.value
//                    viewModel.changeCategory(category)
                },
                colors = ButtonDefaults.buttonColors(
                    if (isSelected.value) Primary else Color.White
//                    if (viewModel.isSelectCheck(category)) PurplePrimary else GreyLighter
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
            ) {
                Text(
                    text = categories[category],
                    color = if (isSelected.value) Color.White else Dark
                    ,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}