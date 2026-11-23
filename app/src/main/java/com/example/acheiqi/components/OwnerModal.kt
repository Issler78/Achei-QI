package com.example.acheiqi.components

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acheiqi.R
import com.example.acheiqi.ui.CustomModal
import com.example.acheiqi.ui.PrimaryButton
import com.example.acheiqi.ui.theme.Chumbo
import com.example.acheiqi.ui.theme.ChumboGrey
import com.example.acheiqi.ui.theme.ChumboLight
import com.example.acheiqi.ui.theme.GreenLight
import com.example.acheiqi.ui.theme.QiRed
import androidx.core.net.toUri

@Composable
fun OwnerModal(
    onDismiss: () -> Unit,
    foundObject: Map<String, Any> // mudar dps quando tiver integracao
){
    val localContext = LocalContext.current
    val scrollState = rememberScrollState()
    val localClipboard = LocalClipboardManager.current // area de transferencia para copiar textos
    val whatsMensage = "Olá, " + foundObject.getValue("finder") +
            "! Vi no AcheiQI que você encontrou meu item: *" +
            foundObject.getValue("nome") as String + "* (achado em " +
            foundObject.getValue("loc") as String + " - " +
            foundObject.getValue("qi") as String +
            "). Onde você está na unidade para eu buscar?"



    // NUMERO PARA TESTE!!
    val telefone = "555497143067"

    CustomModal(
        title = "Falar com quem Encontrou",
        onDismiss = onDismiss,
        content = {
            Column(
                modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp)
                    .verticalScroll(state = scrollState)
            ) {
                // card objeto
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp)
                    ) {
                        Image(
                            painter = painterResource(foundObject.getValue("imagem") as Int),
                            contentDescription = "",
                            alignment = Alignment.Center,
                            modifier = Modifier.size(90.dp)
                                .padding(5.dp)
                                .clip(shape = RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            // categoria
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFDEDE)),
                                elevation = CardDefaults.cardElevation(5.dp),
                            ) {
                                Text(
                                    text = foundObject.getValue("cat") as String,
                                    fontWeight = FontWeight.W900,
                                    color = QiRed,
                                    fontSize = 13.sp,
                                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 12.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))

                            // titulo objeto
                            Text(
                                text = foundObject.getValue("nome") as String,
                                fontSize = 18.sp,
                                color = ChumboGrey,
                                fontWeight = FontWeight.W900,
                                modifier = Modifier.align(alignment = Alignment.Start),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1
                            )

                            // loc exata
                            Text(
                                text = foundObject.getValue("loc") as String,
                                color = Chumbo,
                                fontSize = 14.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.align(alignment = Alignment.Start)
                            )

                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                // card quem encontrou
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 20.dp, horizontal = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Quem encontrou este item:",
                                color = Chumbo,
                                fontSize = 14.sp,
                            )

                            Spacer(modifier = Modifier.width(30.dp))

                            Text(
                                text = foundObject.getValue("roleFinder") as String,
                                color = Chumbo,
                                fontSize = 14.sp,
                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        // quem encontrou
                        Text(
                            text = foundObject.getValue("finder") as String,
                            fontSize = 18.sp,
                            color = ChumboGrey,
                            fontWeight = FontWeight.W900,
                            modifier = Modifier.align(alignment = Alignment.Start),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        HorizontalDivider(
                            color = ChumboLight
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // numero
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.outline_whatsapp_24),
                                    contentDescription = "",
                                    tint = ChumboLight
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = foundObject.getValue("numFinder") as String,
                                    color = Chumbo,
                                    fontSize = 15.sp
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable(onClick = {
                                    localClipboard.setText(AnnotatedString(
                                        text = foundObject.getValue("numFinder") as String
                                    ))
                                })
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_content_copy_24),
                                    contentDescription = "",
                                    tint = ChumboLight,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "Copiar",
                                    color = Chumbo,
                                    fontSize = 15.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                // mensagem whats
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 20.dp, horizontal = 12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "MENSAGEM AUTOMÁTICA:",
                                color = Chumbo,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W900
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable(onClick = {
                                    localClipboard.setText(AnnotatedString(
                                        text = whatsMensage
                                    ))
                                })
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.baseline_content_copy_24),
                                    contentDescription = "",
                                    tint = ChumboLight,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "Copiar",
                                    color = Chumbo,
                                    fontSize = 15.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = whatsMensage,
                            fontSize = 15.sp,
                            color = ChumboLight
                        )
                    }
                }


                Spacer(modifier = Modifier.height(30.dp))

                // botoes
                PrimaryButton(
                    text = "Conversar",
                    icon = R.drawable.outline_whatsapp_24,
                    primaryColor = GreenLight,
                    onClick = {
                        // url para abrir o whats no numero DE TESTE
                        val url = "https://wa.me/${telefone}?text=${Uri.encode(whatsMensage)}"

                        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                        try {
                            localContext.startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(localContext, "WhatsApp não encontrado", Toast.LENGTH_SHORT).show()
                        }
                    },
                )

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    )
}