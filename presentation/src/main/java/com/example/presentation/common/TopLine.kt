package com.example.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.theme.Primary

@Composable
fun TopLine(
    viewModel: CatalogScreenViewModel,
    filterClickAction: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 8.dp, end = 8.dp, top = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .padding(10.dp)
                .clickable { filterClickAction() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.filter),
                contentDescription = null
            )
            if (viewModel.selectedTagList.value.isNotEmpty()) {
                Badge(
                    modifier = Modifier.size(17.dp)
                        .offset(x = 20.dp, y = (-10).dp),
                    containerColor = Primary,
                    contentColor = Color.White,
                    content = {
                        Text(text = viewModel.selectedTagList.value.size.toString())
                    }
                )
            }
        }

        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .size(44.dp)
                .padding(10.dp)
                .clickable { },
            painter = painterResource(id = R.drawable.search),
            contentDescription = null
        )
    }
}