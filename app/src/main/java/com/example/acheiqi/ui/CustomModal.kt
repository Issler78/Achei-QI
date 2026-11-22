package com.example.acheiqi.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.acheiqi.R
import com.example.acheiqi.ui.theme.QiRed

@Composable
fun CustomModal(
    title: String,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
            elevation = CardDefaults.cardElevation(10.dp),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.9f)
                .padding(0.dp)
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                // parte superior titulo
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = QiRed),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
                            .weight(1f)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.logo_achei_qi),
                            contentDescription = "",
                            modifier = Modifier.size(60.dp)
                        )
                        Spacer(Modifier.width(5.dp))

                        // titulo
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "ACHEIQI",
                                    color = Color(0xFFDCDCDC),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    letterSpacing = 0.5.sp
                                )

                                // botao de sair
                                IconButton(
                                    onClick = onDismiss,
                                    modifier = Modifier.size(25.dp)
                                ){
                                    Icon(
                                        painter = painterResource(R.drawable.baseline_close_24),
                                        contentDescription = "",
                                        tint = Color(0xFFDCDCDC),
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }

                            Text(
                                text = title,
                                fontSize = 18.sp,
                                color = Color.White,
                                fontWeight = FontWeight.W900,
                            )
                        }
                    }
                }
            }

            // conteudo do modal
            content()

        }

    }
}