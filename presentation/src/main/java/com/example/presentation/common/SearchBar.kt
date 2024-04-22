package com.example.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.theme.Primary

@Composable
fun SearchBar(
    searchQuery: String,
    newSearchQuery: (String) -> Unit,
    isSearchChanged: () -> Unit,
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = newSearchQuery,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_12)),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.arrow_left), "",
                modifier = Modifier.clickable { isSearchChanged() },
                tint = Primary
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                Icon(
                    painter = painterResource(R.drawable.cancel), "",
                    modifier = Modifier
                        .clickable {
                            isSearchChanged()
                            newSearchQuery("")
                        }
                        .alpha(0.6f),
                    tint = Color.Black
                )
            }
        },
        singleLine = true,
        placeholder = {
            Text(
                stringResource(id = R.string.cart),
                modifier = Modifier.alpha(0.6f),
                fontSize = dimensionResource(id = R.dimen.font_size_17).value.sp,
                lineHeight = dimensionResource(id = R.dimen.font_size_22).value.sp
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        )
    )
}