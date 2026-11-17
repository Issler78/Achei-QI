package com.example.acheiqi.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acheiqi.ui.theme.QiRed

@Composable
fun PrimaryButton(
    text: String,
    icon: Int,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = QiRed,
            contentColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            fontWeight = FontWeight.W900,
            fontSize = 18.sp
        )
    }
}