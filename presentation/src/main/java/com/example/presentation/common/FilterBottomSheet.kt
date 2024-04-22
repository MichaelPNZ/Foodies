package com.example.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.catalog_screen.CatalogScreenViewModel
import com.example.presentation.theme.Primary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    viewModel: CatalogScreenViewModel,
    showBottomSheet: (Boolean) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    ModalBottomSheet(
        onDismissRequest = { showBottomSheet(false) },
        sheetState = sheetState,
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = dimensionResource(id = R.dimen.padding_24),
                    end = dimensionResource(id = R.dimen.padding_24),
                    bottom = dimensionResource(id = R.dimen.padding_32)
                )
        ) {
            Text(
                text = stringResource(id = R.string.choose_dishes),
                fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = dimensionResource(id = R.dimen.font_size_24).value.sp
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding)))

            viewModel.tagList.value.forEachIndexed { index, tag ->
                FilterItem(
                    tag = tag,
                    viewModel = viewModel
                )
                if (index < viewModel.tagList.value.size - 1) {
                    HorizontalDivider()
                }
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.main_padding)))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Primary, RoundedCornerShape(
                            dimensionResource(id = R.dimen.half_padding)
                        )
                    )
                    .clickable {
                        scope.launch {
                            sheetState.hide()
                            showBottomSheet(false)
                        }
                    }
            ) {
                Text(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.main_padding)),
                    text = stringResource(id = R.string.complete),
                    fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                )
            }
        }
    }
}