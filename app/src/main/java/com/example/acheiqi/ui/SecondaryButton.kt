package com.example.acheiqi.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acheiqi.ui.theme.ChumboLight
import com.example.acheiqi.ui.theme.QiRed

@Composable
fun SecondaryButton(
    text: String,
    icon: Int? = null,
    onClick: () -> Unit,
    fontSize: TextUnit = 18.sp
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(50.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 3.dp
        ),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF5F5F5),
            contentColor = ChumboLight
        )
    ) {
        if (icon != null){
            Icon(
                painter = painterResource(icon),
                contentDescription = "",
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = fontSize
        )
    }
}