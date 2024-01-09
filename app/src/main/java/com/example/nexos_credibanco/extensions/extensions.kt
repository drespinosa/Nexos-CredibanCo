package com.example.nexos_credibanco.extensions

import android.content.res.ColorStateList
import android.widget.CheckBox
import com.example.nexos_credibanco.R

/**
 * Cambia dinámicamente los colores del texto y del marcador de verificación de un CheckBox.
 */
fun CheckBox.setCorrectColors(isChecked: Boolean) {
    val textColor = if (isChecked) R.color.background else R.color.black
    setTextColor(ColorStateList.valueOf(context.getColor(textColor)))
    val checkColor = if (isChecked) R.color.background else R.color.colorAccent
    buttonTintList = ColorStateList.valueOf(context.getColor(checkColor))
}
