package com.example.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.presentation.R

@Composable
fun ZeroResultText(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.alpha(0.6f),
            text = text,
            fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
            lineHeight = dimensionResource(id = R.dimen.font_size_24).value.sp,
            textAlign = TextAlign.Center,
        )
    }
}
