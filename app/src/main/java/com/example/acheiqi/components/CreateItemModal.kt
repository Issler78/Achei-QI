package com.example.acheiqi.components

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.acheiqi.R
import com.example.acheiqi.ui.CustomModal
import com.example.acheiqi.ui.CustomSelectInput
import com.example.acheiqi.ui.CustomTextField
import com.example.acheiqi.ui.FileInput
import com.example.acheiqi.ui.PrimaryButton
import com.example.acheiqi.ui.SecondaryButton

@Composable
fun CreateItemModal(
    onDismiss: () -> Unit
){
    // variaveis para os inputs
    val categories = listOf("Documento", "Material Escolar", "Eletrônico", "Vestuário", "Outro")
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

    // variaveis dos inputs (tlvz devera ser na tela de home)
    var objectInputValue by remember { mutableStateOf("") }
    var detailsInputValue by remember { mutableStateOf("") }
    var inputLocationValue by remember { mutableStateOf(locationOptions[0]) }
    var inputCategoryValue by remember { mutableStateOf(categories[0]) }
    var inputExactLoc by remember { mutableStateOf("") }

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
                    modifier = Modifier.align(Alignment.Start)
                )

                // input nome do objeto
                CustomTextField(
                    modifier = Modifier.align(Alignment.Start),
                    textLabel = "OBJETO *",
                    placeholder = "Ex: Mochila preta",
                    value = objectInputValue,
                    onValueChange = { objectInputValue = it },
                    icon = R.drawable.baseline_inventory_24
                )

                // input detalhes
                CustomTextField(
                    modifier = Modifier.align(Alignment.Start),
                    textLabel = "DETALHES DO OBJETO",
                    placeholder = "Descreva detalhes como cor, marca, conteúdo...",
                    value = detailsInputValue,
                    onValueChange = { detailsInputValue = it },
                    icon = R.drawable.baseline_inventory_24,
                    isTextArea = true
                )

                // input categoria
                CustomSelectInput(
                    options = categories,
                    textLabel = "CATEGORIA DO OBJETO *",
                    selectedText = inputCategoryValue,
                    onSelectedOption = { selected ->
                        inputCategoryValue = selected
                    },
                    icon = R.drawable.outline_border_all_24,
                    modifier = Modifier.align(Alignment.Start)
                )

                // input localizacao
                CustomSelectInput(
                    options = locationOptions,
                    textLabel = "UNIDADE QI *",
                    selectedText = inputLocationValue,
                    onSelectedOption = { selected ->
                        inputLocationValue = selected
                    },
                    icon = R.drawable.baseline_location_on_24,
                    modifier = Modifier.align(Alignment.Start)
                )

                // input local exato
                CustomTextField(
                    modifier = Modifier.align(Alignment.Start),
                    textLabel = "LOCAL EXATO ONDE ENCONTROU *",
                    placeholder = "Ex: Laboratório de Informática, Sala 302",
                    value = inputExactLoc,
                    onValueChange = { inputExactLoc = it },
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
                        onClick = {}
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    )
}