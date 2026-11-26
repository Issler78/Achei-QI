package com.example.acheiqi

import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.acheiqi.components.Footer
import com.example.acheiqi.ui.CustomSelectInput
import com.example.acheiqi.ui.CustomTextField
import com.example.acheiqi.ui.PrimaryButton
import com.example.acheiqi.ui.theme.Chumbo
import com.example.acheiqi.ui.theme.ChumboGrey
import com.example.acheiqi.ui.theme.QiRed
import com.example.acheiqi.utils.PhoneVisualTransformation
import com.example.acheiqi.viewmodels.RegisterViewModel

@Composable
fun RegisterPage(navController: NavController, viewModel: RegisterViewModel){
    val context = LocalContext.current
    val formState = viewModel.formState

    // variaveis para os inputs
    val affiliationOptions = listOf("Aluno QI", "Professor QI", "Funcionário QI")
    val unitOptions = listOf(
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



    // mensagem de erro
    LaunchedEffect(formState.error) {
        formState.error?.let { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
            viewModel.clearError()
        }
    }

    // sucesso
    LaunchedEffect(formState.success) {
        if (formState.success) {
            Toast.makeText(
                context,
                "Cadastro realizado com sucesso! Para confirmar, realize o login.",
                Toast.LENGTH_LONG
            ).show()

            navController.navigate("login") {
                popUpTo("register") { inclusive = true }
            }
        }
    }

    // tela de loading
    if (formState.loading) {
        Dialog(onDismissRequest = {}) {
            CircularProgressIndicator(color = QiRed)
        }
    }



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
                        value = formState.name,
                        onValueChange = { newValue ->
                            viewModel.updateForm { it.copy(name = newValue) }
                        },
                        icon = R.drawable.baseline_person_24
                    )

                    // input email
                    CustomTextField(
                        modifier = Modifier.align(Alignment.Start),
                        textLabel = "E-MAIL *",
                        placeholder = "seuemail@gmail.com",
                        value = formState.email,
                        onValueChange = { newValue ->
                            viewModel.updateForm { it.copy(email = newValue) }
                        },
                        icon = R.drawable.baseline_email_24
                    )

                    // input password
                    CustomTextField(
                        modifier = Modifier.align(Alignment.Start),
                        textLabel = "SENHA *",
                        placeholder = "Mínimo 6 caracteres",
                        value = formState.password,
                        onValueChange = { newValue ->
                            viewModel.updateForm { it.copy(password = newValue) }
                        },
                        icon = R.drawable.outline_lock_24,
                        visualTransformation = PasswordVisualTransformation()
                    )

                    // input whatsapp
                    CustomTextField(
                        modifier = Modifier.align(Alignment.Start),
                        textLabel = "WHATSAPP (COM DDD) *",
                        placeholder = "(54) 12345-6789",
                        value = formState.phone,
                        onValueChange = { newValue ->
                            val numbers = newValue.filter { it.isDigit() }
                            if (numbers.length <= 11) {
                                viewModel.updateForm { it.copy(phone = numbers) }
                            }
                        },
                        icon = R.drawable.outline_whatsapp_24,
                        visualTransformation = PhoneVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )

                    // input vinculo
                    CustomSelectInput(
                        options = affiliationOptions,
                        textLabel = "VÍNCULO COM A QI *",
                        selectedText = formState.affiliation,
                        onSelectedOption = { selected ->
                            viewModel.updateForm { it.copy(affiliation = selected) }
                        },
                        icon = R.drawable.baseline_person_24,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    // input localizacao
                    CustomSelectInput(
                        options = unitOptions,
                        textLabel = "SUA UNIDADE QI *",
                        selectedText = formState.unit,
                        onSelectedOption = { selected ->
                            viewModel.updateForm { it.copy(unit = selected) }
                        },
                        icon = R.drawable.baseline_location_on_24,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Spacer(modifier = Modifier.height(25.dp))



                    // botao cadastrar
                    PrimaryButton(
                        text = "Cadastrar",
                        icon = R.drawable.outline_login_24,
                        onClick = { viewModel.register() }
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