package com.example.presentation.personal_account_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.auth.UserData
import com.example.presentation.theme.Dark
import com.example.presentation.theme.Primary

@Composable
fun PersonalAccountScreen(
    personalAccountViewModel: PersonalAccountViewModel = hiltViewModel(),
    userData: UserData?,
    onSignOutClick: () -> Unit,
    navigateToSoppingCartDetail: (index: Int) -> Unit
) {
    val user =  personalAccountViewModel.user.value

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (userData != null) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .height(100.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AsyncImage(
                    model = userData.profilePictureUrl,
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                userData.userName?.let {
                    Text(
                        text = it,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }

        if (user?.shoppingCartList != null) {
            Text(
                modifier = Modifier
                    .alpha(0.87f),
                text = "Список заказов",
                fontSize = 16.sp,
            )

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f)
                    .fillMaxWidth(),
            ) {
                items(user.shoppingCartList.size) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .padding(12.dp)
                            .clickable { navigateToSoppingCartDetail(index) },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "Заказ No${index + 1}",
                            color = Dark,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    HorizontalDivider()
                }
            }
        }

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(constraints.maxWidth.toFloat())
                    .height(55.dp),
                onClick = {
                    onSignOutClick()
                    personalAccountViewModel.signOut()
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(Primary),
            ) {
                Text(
                    text = "Выйти",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        }
    }
}