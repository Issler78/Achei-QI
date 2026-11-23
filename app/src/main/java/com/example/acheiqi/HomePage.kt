package com.example.acheiqi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.acheiqi.components.CreateItemModal
import com.example.acheiqi.components.Footer
import com.example.acheiqi.components.ItemCard
import com.example.acheiqi.components.OwnerModal
import com.example.acheiqi.ui.PrimaryButton
import com.example.acheiqi.ui.theme.Background
import com.example.acheiqi.ui.theme.Chumbo
import com.example.acheiqi.ui.theme.ChumboGrey
import com.example.acheiqi.ui.theme.ChumboLight
import com.example.acheiqi.ui.theme.QiRed

@Composable
fun HomePage(navController: NavController){
    // OBJETO PARA TESTE - EXCLUIR DPS DA INTEGRACAO COM A API
    val objeto = mapOf(
        "imagem" to R.drawable.photo1622560481979f5b0174242a0,
        "nome" to "Mochila Preta",
        "cat" to "Material Escolar",
        "loc" to "Laboratório de Informática - Sala 302",
        "qi" to "Caxias do Sul",
        "finder" to "Matheus Issler",
        "numFinder" to "(54) 99657-3462",
        "roleFinder" to "Aluno QI"
    )


    // modal
    var isOpenCreateModal by remember { mutableStateOf(false) }
    var isOpenOwnerModal by remember { mutableStateOf(false) }



    // map com as categorias e icones
    val categories = mapOf(
        "Todos" to R.drawable.outline_border_all_24,
        "Documentos" to R.drawable.outline_article_person_24,
        "Material Escolar" to R.drawable.sharp_book_ribbon_24,
        "Eletrônicos" to R.drawable.baseline_laptop_chromebook_24,
        "Vestuário" to R.drawable.outline_checkroom_24,
        "Outros" to R.drawable.baseline_inventory_24
    )
    var selectedCategory by remember { mutableIntStateOf(0) }


    // create modal
    if (isOpenCreateModal){
        CreateItemModal(
            onDismiss = { isOpenCreateModal = false }
        )
    }

    // owner modal
    if(isOpenOwnerModal){
        OwnerModal(
            onDismiss = { isOpenOwnerModal = false },
            foundObject = objeto
        )
    }


    // conteudo principal
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // card inicial
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp, horizontal = 15.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFDEDE)),
                        elevation = CardDefaults.cardElevation(10.dp)
                    ) {
                        Text(
                            text = "MURAL COMUNITÁRIO QI",
                            fontWeight = FontWeight.W900,
                            color = QiRed,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(vertical = 5.dp, horizontal = 12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    // titulo
                    Text(
                        text = "Perdeu algo na unidade?",
                        fontWeight = FontWeight.W900,
                        fontSize = 22.sp,
                        color = ChumboGrey
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Encontre aqui.",
                        fontWeight = FontWeight.W900,
                        fontSize = 22.sp,
                        color = ChumboGrey
                    )

                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "O AcheiQI conecta diretamente quem achou e quem perdeu pertences na QI Faculdade & Escola Técnica.",
                        fontSize = 15.sp,
                        color = ChumboGrey,
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    PrimaryButton(
                        text = "Publicar Objeto Encontrado",
                        onClick = { isOpenCreateModal = true },
                        icon = R.drawable.outline_add_24,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))
            HorizontalDivider(
                color = ChumboLight
            )
            Spacer(modifier = Modifier.height(25.dp))



            // filtrar por categoria
            Text(
                text = "FILTRAR POR CATEGORIA",
                fontSize = 15.sp,
                color = Chumbo,
                fontWeight = FontWeight.W900,
                modifier = Modifier.align(alignment = Alignment.Start)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                // para cada categoria
                itemsIndexed(categories.entries.toList()) { index, (category, icon) ->
                    FilterChip(
                        selected = index == selectedCategory,
                        onClick = { selectedCategory = index },
                        shape = RoundedCornerShape(10.dp),
                        leadingIcon = { Icon(
                            painter = painterResource(icon),
                            contentDescription = "",
                            modifier = Modifier.padding(
                                start = 5.dp
                            )
                        ) },
                        label = { Text(
                            text = category,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(
                                end = 5.dp
                            )
                        ) },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = Color.White,
                            labelColor = Chumbo,
                            iconColor = Chumbo,
                            selectedContainerColor = QiRed,
                            selectedLabelColor = Color.White,
                            selectedLeadingIconColor = Color.White
                        )
                    )

                }
            }

            Spacer(modifier = Modifier.height(10.dp))



            // cards
            // precisa ter uma nova list para buscar todos os items salvos e fazer um foreach
            ItemCard(
                title = "Mochila Preta",
                qi = "Caxias do Sul",
                location = "Laboratório de Informática - Sala 302",
                finder = "Matheus Issler",
                onClick = {
                    // funcao teste!!! mudar dps quando tiver integracao
                    isOpenOwnerModal = true
                }
            )
            ItemCard(
                title = "Mochila Preta",
                qi = "Caxias do Sul",
                location = "Laboratório de Informática - Sala 302",
                finder = "Matheus Issler",
                onClick = {}
            )
            ItemCard(
                title = "Mochila Preta",
                qi = "Caxias do Sul",
                location = "Laboratório de Informática - Sala 302",
                finder = "Matheus Issler",
                onClick = {}
            )
        }
        Footer()
    }
}