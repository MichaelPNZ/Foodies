package com.example.presentation.login_screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.auth.SignInState
import com.example.presentation.R
import com.example.presentation.theme.Primary

@Composable
fun LoginScreen(
    state: SignInState,
    onSignInClick: () -> Unit,
    navigateToCatalogScreen: () -> Unit,
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(dimensionResource(id = R.dimen.padding_20)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.padding_55))
                .padding(horizontal = dimensionResource(id = R.dimen.main_padding)),
            onClick = navigateToCatalogScreen,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_12)),
            colors = ButtonDefaults.buttonColors(Primary),
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.enter_without_registration),
                color = Color.White,
                fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_10)))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.padding_55))
                .padding(horizontal = dimensionResource(id = R.dimen.main_padding)),
            onClick = onSignInClick,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_12)),
            colors = ButtonDefaults.buttonColors(Primary),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.half_padding)),
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.google_icon),
                    contentDescription = null,
                    modifier = Modifier.wrapContentSize()
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.Sign_in_with_Google),
                    color = Color.White,
                    fontSize = dimensionResource(id = R.dimen.font_size_16).value.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        }
    }
}
