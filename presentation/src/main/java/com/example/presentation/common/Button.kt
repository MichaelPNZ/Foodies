package com.example.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.theme.Primary
import com.example.utils.Constants

@Composable
fun Button(
    sum: String,
    navigateToShoppingCart: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = dimensionResource(id = R.dimen.main_padding),
                vertical = dimensionResource(id = R.dimen.padding_12)
            )
            .background(Primary, RoundedCornerShape(dimensionResource(id = R.dimen.half_padding)))
            .clickable { navigateToShoppingCart() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.main_padding),
                    vertical = dimensionResource(id = R.dimen.padding_14)
                ),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(dimensionResource(id = R.dimen.padding_24))
                    .padding(end = dimensionResource(id = R.dimen.half_padding)),
                painter = painterResource(id = R.drawable.cart),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White)
            )

            Text(
                text = "$sum ${Constants.RUR}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }
}