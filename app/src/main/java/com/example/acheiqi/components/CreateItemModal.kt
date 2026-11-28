package com.example.acheiqi.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.acheiqi.R
import com.example.acheiqi.ui.CustomModal
import com.example.acheiqi.ui.CustomSelectInput
import com.example.acheiqi.ui.CustomTextField
import com.example.acheiqi.ui.FileInput
import com.example.acheiqi.ui.PrimaryButton
import com.example.acheiqi.ui.SecondaryButton
import com.example.acheiqi.ui.theme.QiRed
import com.example.acheiqi.viewmodels.CreateItemViewModel

@Composable
fun CreateItemModal(
    onDismiss: () -> Unit,
    viewModel: CreateItemViewModel,
    navController: NavController
){
    val context = LocalContext.current
    val formState = viewModel.formState

    // variaveis para os inputs
    val categories = listOf("Documentos", "Materiais Escolares", "Eletrônicos", "Vestuário", "Outro")
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
    val scrollState = rememberScrollState()



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
                "Objeto publicado com sucesso!",
                Toast.LENGTH_LONG
            ).show()

            onDismiss()
            navController.navigate("home")
        }
    }

    // tela de loading
    if (formState.loading) {
        Dialog(onDismissRequest = {}) {
            CircularProgressIndicator(color = QiRed)
        }
    }

    CustomModal(
        title = "Publicar Objeto Encontrado",
        onDismiss = onDismiss,
        content = {
            Column(
                modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp)
                    .verticalScroll(state = scrollState)
            ) {
                // formulario

                // input de arquivo
                FileInput(
                    modifier = Modifier.align(Alignment.Start),
                    image = formState.image,
                    onValueChange = { newValue ->
                        viewModel.updateForm { it.copy(image = newValue) }
                    }
                )

                // input nome do objeto
                CustomTextField(
                    modifier = Modifier.align(Alignment.Start),
                    textLabel = "OBJETO *",
                    placeholder = "Ex: Mochila preta",
                    value = formState.title,
                    onValueChange = { newValue ->
                        viewModel.updateForm { it.copy(title = newValue) }
                    },
                    icon = R.drawable.baseline_inventory_24
                )

                // input detalhes
                CustomTextField(
                    modifier = Modifier.align(Alignment.Start),
                    textLabel = "DETALHES DO OBJETO",
                    placeholder = "Descreva detalhes como cor, marca, conteúdo...",
                    value = formState.description ?: "",
                    onValueChange = { newValue ->
                        viewModel.updateForm { it.copy(description = newValue) }
                    },
                    icon = R.drawable.baseline_inventory_24,
                    isTextArea = true
                )

                // input categoria
                CustomSelectInput(
                    options = categories,
                    textLabel = "CATEGORIA DO OBJETO *",
                    selectedText = formState.category,
                    onSelectedOption = { newValue ->
                        viewModel.updateForm { it.copy(category = newValue) }
                    },
                    icon = R.drawable.outline_border_all_24,
                    modifier = Modifier.align(Alignment.Start)
                )

                // input localizacao
                CustomSelectInput(
                    options = locationOptions,
                    textLabel = "UNIDADE QI *",
                    selectedText = formState.unit,
                    onSelectedOption = { newValue ->
                        viewModel.updateForm { it.copy(unit = newValue) }
                    },
                    icon = R.drawable.baseline_location_on_24,
                    modifier = Modifier.align(Alignment.Start)
                )

                // input local exato
                CustomTextField(
                    modifier = Modifier.align(Alignment.Start),
                    textLabel = "LOCAL EXATO ONDE ENCONTROU *",
                    placeholder = "Ex: Laboratório de Informática, Sala 302",
                    value = formState.location,
                    onValueChange = { newValue ->
                        viewModel.updateForm { it.copy(location = newValue) }
                    },
                    icon = R.drawable.baseline_location_on_24
                )

                Spacer(modifier = Modifier.height(10.dp))

                // botoes
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SecondaryButton(
                        text = "Cancelar",
                        onClick = onDismiss,
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    PrimaryButton(
                        text = "Publicar",
                        onClick = {
                            viewModel.save(context)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    )
}