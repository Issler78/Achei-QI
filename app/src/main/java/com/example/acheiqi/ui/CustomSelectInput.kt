package com.example.acheiqi.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acheiqi.ui.theme.ChumboGrey
import com.example.acheiqi.ui.theme.GreyLight

@OptIn(ExperimentalMaterial3Api::class) // determina poder usar um elemento experimental (exposedDropdownMenuBox)
@Composable
fun CustomSelectInput(
    options: List<String>,
    textLabel: String,
    selectedText: String,
    onSelectedOption: (String) -> Unit,
    icon: Int,
    modifier: Modifier = Modifier
){
    var expandedState by remember { mutableStateOf(false) }

    Text(
        text = textLabel,
        modifier = modifier,
        fontWeight = FontWeight.W900,
        color = ChumboGrey,
        fontSize = 15.sp
    )
    Spacer(modifier = Modifier.height(3.dp))
    ExposedDropdownMenuBox(
        expanded = expandedState,
        onExpandedChange = { expandedState = !expandedState }
    ) {
        OutlinedTextField(
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            value = selectedText,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.5.dp,
                    color = GreyLight,
                    shape = RoundedCornerShape(13.dp)
                )
                .menuAnchor(
                    type = ExposedDropdownMenuAnchorType.PrimaryNotEditable
                ),
            textStyle = TextStyle(fontSize = 16.sp),
            shape = RoundedCornerShape(13.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            leadingIcon = { Icon(
                painter = painterResource(icon),
                contentDescription = "",
                tint = ChumboGrey
            ) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedState) }
        )

        ExposedDropdownMenu(
            expanded = expandedState,
            onDismissRequest = { expandedState = false }
        ) {
            // percorrendo cada opcao, para cada uma, criar um item
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onSelectedOption(option)
                        expandedState = false
                    }
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}