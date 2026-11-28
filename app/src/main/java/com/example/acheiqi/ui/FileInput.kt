package com.example.acheiqi.ui

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.acheiqi.ui.theme.ChumboGrey
import com.example.acheiqi.ui.theme.GreyLight

@Composable
fun FileInput(
    modifier: Modifier,
    image: Uri?,
    onValueChange: (Uri?) -> Unit
){
    // launcher da tela de permissao
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                onValueChange(uri)
            }
        }
    )



    // input
    Text(
        text = "FOTO DO OBJETO ENCONTRADO *",
        modifier = modifier,
        fontWeight = FontWeight.W900,
        color = ChumboGrey,
        fontSize = 15.sp
    )
    Spacer(modifier = Modifier.height(3.dp))
    Button(
        onClick = {
            launcher.launch(input = "image/*")
        },
        modifier = Modifier.fillMaxWidth()
            .border(
                width = 1.5.dp,
                color = GreyLight,
                shape = RoundedCornerShape(13.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF5F5F5),
            contentColor = ChumboGrey
        )
    ) {
        Text(
            text = if(image == null) "Escolha uma foto..." else "Trocar foto..."
        )
    }
    Spacer(modifier = Modifier.height(5.dp))

    // foto escolhida
    if (image != null){
        AsyncImage(
            model = image,
            contentDescription = "",
            modifier = Modifier.fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.FillWidth
        )
    }

    Spacer(modifier = Modifier.height(20.dp))
}