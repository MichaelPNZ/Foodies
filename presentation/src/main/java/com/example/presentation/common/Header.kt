package com.example.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.theme.Primary

@Composable
fun Header(
    title: String,
    navigateBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.padding_56))
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.main_padding)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.padding_24))
                .clickable { navigateBack() },
            painter = painterResource(id = R.drawable.arrow_left),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Primary)
        )

        Text(
            modifier = Modifier
                .padding(start = dimensionResource(id = R.dimen.padding_30))
                .alpha(0.87f),
            text = title,
            fontSize = dimensionResource(id = R.dimen.font_size_18).value.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}