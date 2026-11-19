package com.example.acheiqi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acheiqi.R
import com.example.acheiqi.ui.PrimaryButton
import com.example.acheiqi.ui.theme.Chumbo
import com.example.acheiqi.ui.theme.ChumboGrey
import com.example.acheiqi.ui.theme.ChumboLight
import com.example.acheiqi.ui.theme.QiRed

@Composable
fun ItemCard(
    title: String,
    qi: String,
    location: String,
    finder: String, // tlvz colocar o perfil do encontrador para a tela de detalhes
    // falta data encontrada. decidir qual sera o formato
    // falta imagem tambem
    onClick: () -> Unit
){
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEB)),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                Image(
                    painter = painterResource(R.drawable.photo1622560481979f5b0174242a0),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.FillWidth
                )



                Row(
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp)
                            .background(
                                color = Color.Black.copy(alpha = 0.6f),
                                shape = RoundedCornerShape(20.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(
                            painter = painterResource(R.drawable.baseline_location_on_24),
                            contentDescription = "",
                            tint = QiRed,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = qi,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 2.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                // titulo do item
                Text(
                    text = title,
                    fontSize = 20.sp,
                    color = ChumboGrey,
                    fontWeight = FontWeight.W900,
                    modifier = Modifier.align(alignment = Alignment.Start)
                )
                Spacer(modifier = Modifier.height(10.dp))

                // localizacao
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_location_on_24),
                        contentDescription = "",
                        tint = QiRed
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = location,
                        color = Chumbo,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                // tempo que foi encontrado
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_calendar_month_24),
                        contentDescription = "",
                        tint = ChumboLight
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Há 2 dias",
                        color = Chumbo,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                // quem encontrou
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_person_24),
                        contentDescription = "",
                        tint = ChumboLight
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Encontrado por: ",
                        color = Chumbo,
                        fontSize = 14.sp
                    )
                    Text(
                        text = finder,
                        color = Chumbo,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                PrimaryButton(
                    text = "Sou o Dono",
                    icon = R.drawable.outline_whatsapp_24,
                    onClick = onClick
                )

                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}