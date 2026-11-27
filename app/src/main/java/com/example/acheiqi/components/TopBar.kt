package com.example.acheiqi.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.acheiqi.R
import com.example.acheiqi.ui.theme.Chumbo
import com.example.acheiqi.ui.theme.ChumboGrey
import com.example.acheiqi.ui.theme.QiRed
import com.example.acheiqi.utils.TokenManager

@Composable
fun TopBar(navController: NavController){
    // ve qual a tela atual
    val currentScreen by navController.currentBackStackEntryAsState()
    val currentRoute = currentScreen?.destination?.route

    // esta logado ou nao
    val isLoggedIn: Boolean = TokenManager.getToken() != null

    val textBtn: String?
    val iconBtn: Int?
    val routeBtn: String?
    when(currentRoute){
        "register", "login" -> {
            textBtn = "Mural"
            iconBtn = R.drawable.baseline_house_24
            routeBtn = "home"
        }
        "home" -> {
            if (isLoggedIn){
                textBtn = "Sair"
                iconBtn = R.drawable.baseline_person_24
                routeBtn = "login"
            } else {
                textBtn = "Cadastrar"
                iconBtn = R.drawable.baseline_person_24
                routeBtn = "register"
            }
        }
        else -> {
            if (isLoggedIn){
                textBtn = "Sair"
                iconBtn = R.drawable.baseline_person_24
                routeBtn = "login"
            } else {
                textBtn = "Cadastrar"
                iconBtn = R.drawable.baseline_person_24
                routeBtn = "register"
            }
        }
    }



    Surface(
        shadowElevation = 10.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth()
            .padding(20.dp, 5.dp, 20.dp, 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.logo_achei_qi),
                    contentDescription = "",
                    modifier = Modifier.size(45.dp)
                )
                Spacer(Modifier.width(5.dp))

                // titulo
                Column(horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Row(
                        modifier = Modifier.padding(top = 5.dp)
                    ) {
                        Text(
                            text = "Achei",
                            fontWeight = FontWeight.W900,
                            color = Color.Black,
                            fontSize = 21.sp
                        )
                        Text(
                            text = "QI",
                            fontWeight = FontWeight.W900,
                            color = QiRed,
                            fontSize = 21.sp
                        )
                    }

                    Text(
                        text = "Mural Comunitário de Achados & Perdidos",
                        fontSize = 10.sp,
                        color = ChumboGrey
                    )
                }
            }

            // botao de navegacao
            Surface(
                onClick = {
                    if(isLoggedIn){
                        TokenManager.clearToken()
                    }
                    navController.navigate(route = routeBtn)
                },
                shape = CircleShape,
                shadowElevation = 5.dp
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 7.dp)
                ) {
                    Text(
                        text = textBtn,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.W500,
                        color = Chumbo,
                        modifier = Modifier.padding(start = 4.dp, top = 7.dp, bottom = 7.dp, end = 3.dp)
                    )

                    Icon(
                        painter = painterResource(iconBtn),
                        contentDescription = "",
                        tint = Chumbo,
                        modifier = Modifier.size(19.dp).padding()
                    )
                }
            }
        }
    }
}