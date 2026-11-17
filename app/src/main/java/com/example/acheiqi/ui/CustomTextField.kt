package com.example.acheiqi.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acheiqi.ui.theme.ChumboGrey
import com.example.acheiqi.ui.theme.ChumboLight
import com.example.acheiqi.ui.theme.GreyLight

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    textLabel: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: Int,
    visualTransformation: VisualTransformation? = null,
    keyboardOptions: KeyboardOptions? = null
){
    Text(
        text = textLabel,
        modifier = modifier,
        fontWeight = FontWeight.W900,
        color = ChumboGrey,
        fontSize = 15.sp
    )
    Spacer(modifier = Modifier.height(3.dp))
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation ?: VisualTransformation.None,
        modifier = Modifier.fillMaxWidth()
            .border(
                width = 1.5.dp,
                color = GreyLight,
                shape = RoundedCornerShape(13.dp)
            ),
        placeholder = { Text(
            text = placeholder,
            color = ChumboLight,
            fontSize = 16.sp
        )
        },
        singleLine = true,
        textStyle = TextStyle(fontSize = 16.sp),
        leadingIcon = { Icon(
            painter = painterResource(icon),
            contentDescription = "",
            tint = ChumboGrey
        ) },
        shape = RoundedCornerShape(13.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent
        ),
        keyboardOptions = keyboardOptions ?: KeyboardOptions.Default
    )
    Spacer(modifier = Modifier.height(20.dp))
}