package com.example.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.domain.model.Tag
import com.example.presentation.R
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.theme.Primary

@Composable
fun FilterItem(
    tag: Tag,
    viewModel: CatalogScreenViewModel,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .toggleable(
                value = viewModel.selectedTagList.value.contains(tag),
                onValueChange = {
                    viewModel.checkedSelectedTagList(tag)
                },
                role = Role.Checkbox
            )
            .padding(vertical = dimensionResource(id = R.dimen.padding_12)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.half_padding))
    ) {
        Text(
            text = tag.name,
            fontWeight = FontWeight.Normal,
            fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
            lineHeight = dimensionResource(id = R.dimen.font_size_24).value.sp,
            modifier = Modifier.weight(1f)
        )

        Checkbox(
            checked = viewModel.selectedTagList.value.contains(tag),
            onCheckedChange = null,
            colors = CheckboxDefaults.colors(
                checkedColor = Primary,
                uncheckedColor = Color.Gray)
        )
    }
}