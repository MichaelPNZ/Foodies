package com.example.foodies.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodies.R

@Composable
fun TopLine(
    filterClickAction: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 16.dp)
    ) {
        Image(
            modifier = Modifier.size(44.dp)
                .padding(10.dp)
                .clickable { filterClickAction() },
            painter = painterResource(id = R.drawable.filter),
            contentDescription = null
        )

        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )

        Image(
            modifier = Modifier.size(44.dp)
                .padding(10.dp)
                .clickable {  },
            painter = painterResource(id = R.drawable.search),
            contentDescription = null
        )
    }
}