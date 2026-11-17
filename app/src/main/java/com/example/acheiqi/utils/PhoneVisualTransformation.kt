package com.example.acheiqi.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val digits = text.text.take(11) // usuario digitou
        val output = StringBuilder() // usuario esta enxergando

        // percorre cada digito, verifica a posicao e se necessario insere um elemento da mascara
        for (i in digits.indices) {
            if (i == 0) output.append("(")
            output.append(digits[i])
            if (i == 1) output.append(") ")
            if (i == 6) output.append("-")
        }

        // corrigindo a posicao dos indices dos digitos
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (digits.isEmpty()) return 0
                if (offset == 0) return 1
                if (offset <= 2) return offset + 1
                if (offset <= 7) return offset + 3
                return offset + 4
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (digits.isEmpty() || offset <= 1) return 0
                if (offset <= 3) return offset - 1
                if (offset <= 5) return 2
                if (offset <= 10) return offset - 3
                return (offset - 4).coerceAtMost(digits.length)
            }
        }

        return TransformedText(AnnotatedString(output.toString()), offsetMapping)
    }
}