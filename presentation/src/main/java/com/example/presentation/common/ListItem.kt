package com.example.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.presentation.R

@Composable
fun ListItem(
    category: String,
    measure: String,
    unit: String,
) {
    Column(Modifier
        .fillMaxWidth()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(
                    vertical = dimensionResource(id = R.dimen.padding_12),
                    horizontal = dimensionResource(id = R.dimen.main_padding)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.half_padding))
        ) {
            Text(
                text = category,
                fontWeight = FontWeight.Normal,
                fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                lineHeight = dimensionResource(id = R.dimen.font_size_24).value.sp,
                modifier = Modifier
                    .weight(1f)
                    .alpha(0.6f)
            )
            Text(
                text = "$measure $unit",
                fontWeight = FontWeight.Normal,
                fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                lineHeight = dimensionResource(id = R.dimen.font_size_24).value.sp,
                modifier = Modifier.alpha(0.9f)
            )
        }
        HorizontalDivider()
    }
}