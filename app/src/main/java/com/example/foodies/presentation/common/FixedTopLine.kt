package com.example.foodies.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun FixedTopLine() {
    Column {
        TopLine()
//        Spacer(modifier = Modifier.size(8.dp))
//        CategoriesRow(categories = listOf("Category 1", "Category 2", "Category 3", "Category 4"))
//        Spacer(modifier = Modifier.size(8.dp))
    }
}