package com.example.acheiqi

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.acheiqi.components.Footer
import com.example.acheiqi.ui.CustomSelectInput
import com.example.acheiqi.ui.CustomTextField
import com.example.acheiqi.ui.PrimaryButton
import com.example.acheiqi.ui.theme.Background
import com.example.acheiqi.ui.theme.Chumbo
import com.example.acheiqi.ui.theme.ChumboGrey
import com.example.acheiqi.ui.theme.QiRed
import com.example.acheiqi.utils.PhoneVisualTransformation

@Composable
fun RegisterPage(navController: NavController){
    // variaveis para os inputs
    val roleOptions = listOf("Aluno QI", "Professor QI", "Funcionário / Secretaria")
    val locationOptions = listOf(
        "Alvorada",
        "Canoas",
        "Caxias do Sul",
        "Esteio",
        "Gravataí",
        "Guaíba",
        "Novo Hamburgo",
        "Porto Alegre - Alberto Bins",
        "Porto Alegre - Assis Brasil",
        "Porto Alegre - Juca Batista",
        "Rio Grande",
        "São Leopoldo",
        "Viamão",
    )

    // variaveis de inputs
    var inputNameValue by remember { mutableStateOf("") }
    var inputEmailValue by remember { mutableStateOf("") }
    var inputPasswordValue by remember { mutableStateOf("") }
    var inputPhoneValue by remember { mutableStateOf("") }
    var inputRoleValue by remember { mutableStateOf(roleOptions[0]) }
    var inputLocationValue by remember { mutableStateOf(locationOptions[0]) }


    Column(
        modifier = Modifier.fillMaxSize()
        .verticalScroll(rememberScrollState())
        .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // botao voltar ao mural
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "",
                    tint = Chumbo,
                    modifier = Modifier.size(18.dp)
                        .clickable(onClick = {
                            navController.navigate("home")
                        })
                )
                Spacer(Modifier.width(2.dp))
                Text(
                    text = "Voltar ao mural sem cadastrar",
                    fontSize = 14.sp,
                    color = ChumboGrey,
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate("home")
                    })
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.elevatedCardElevation(20.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo_achei_qi),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp).fillMaxWidth()
                    )

                    Row(
                        modifier = Modifier.padding(top = 2.dp, bottom = 15.dp)
                    ) {
                        Text(
                            text = "Achei",
                            fontWeight = FontWeight.W900,
                            color = Color.Black,
                            fontSize = 23.sp
                        )
                        Text(
                            text = "QI",
                            fontWeight = FontWeight.W900,
                            color = QiRed,
                            fontSize = 23.sp
                        )
                    }

                    Text(
                        text = "Identifique-se com seu e-mail e telefone para publicar novos itens e receber contato direto dos donos pelo WhatsApp.",
                        color = ChumboGrey,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(40.dp))



                    // formulario

                    // input nome
                    CustomTextField(
                        modifier = Modifier.align(Alignment.Start),
                        textLabel = "NOME COMPLETO *",
                        placeholder = "Ex: Lucas de Souza",
                        value = inputNameValue,
                        onValueChange = { inputNameValue = it },
                        icon = R.drawable.baseline_person_24
                    )

                    // input email
                    CustomTextField(
                        modifier = Modifier.align(Alignment.Start),
                        textLabel = "E-MAIL *",
                        placeholder = "seuemail@gmail.com",
                        value = inputEmailValue,
                        onValueChange = { inputEmailValue = it },
                        icon = R.drawable.baseline_email_24
                    )

                    // input password
                    CustomTextField(
                        modifier = Modifier.align(Alignment.Start),
                        textLabel = "SENHA *",
                        placeholder = "Mínimo 6 caracteres",
                        value = inputPasswordValue,
                        onValueChange = { inputPasswordValue = it },
                        icon = R.drawable.outline_lock_24,
                        visualTransformation = PasswordVisualTransformation()
                    )

                    // input whatsapp
                    CustomTextField(
                        modifier = Modifier.align(Alignment.Start),
                        textLabel = "WHATSAPP (COM DDD) *",
                        placeholder = "(54) 12345-6789",
                        value = inputPhoneValue,
                        onValueChange = { value ->
                            val numbers = value.filter { it.isDigit() }
                            if (numbers.length <= 11) {
                                inputPhoneValue = numbers
                            }
                        },
                        icon = R.drawable.outline_whatsapp_24,
                        visualTransformation = PhoneVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )

                    // input vinculo
                    CustomSelectInput(
                        options = roleOptions,
                        textLabel = "VÍNCULO COM A QI *",
                        selectedText = inputRoleValue,
                        onSelectedOption = { selected ->
                            inputRoleValue = selected
                        },
                        icon = R.drawable.baseline_person_24,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    // input localizacao
                    CustomSelectInput(
                        options = locationOptions,
                        textLabel = "SUA UNIDADE QI *",
                        selectedText = inputLocationValue,
                        onSelectedOption = { selected ->
                            inputLocationValue = selected
                        },
                        icon = R.drawable.baseline_location_on_24,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(25.dp))



                    // botao cadastrar
                    PrimaryButton(
                        text = "Cadastrar",
                        icon = R.drawable.outline_login_24,
                        onClick = {
                            // aqui vira uma funcao que verifica os campos

                            // aqui vira a chamada para uma funcao de um controller que salvara no banco ???

                            // aqui vira uma funcao para salvar os dados do usuario no SharedPreferences

                            // aqui vira o navigate
                        }
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Já possui uma conta? ",
                            fontWeight = FontWeight.W400,
                            color = Chumbo,
                            fontSize = 15.sp
                        )
                        Text(
                            text = "Faça o login",
                            fontWeight = FontWeight.Bold,
                            color = QiRed,
                            fontSize = 15.sp,
                            modifier = Modifier.clickable(onClick = {
                                navController.navigate("login")
                            })
                        )
                    }

                }
            }
        }

        Footer()
    }
}