package com.example.nexos_credibanco.extensions

import android.content.res.ColorStateList
import android.widget.CheckBox
import com.example.nexos_credibanco.R
import java.math.BigDecimal
import java.text.DecimalFormat

fun CheckBox.setCorrectColors(isChecked: Boolean) {
    val textColor = if (isChecked) R.color.background else R.color.black
    setTextColor(ColorStateList.valueOf(context.getColor(textColor)))
    val checkColor = if (isChecked) R.color.background else R.color.colorAccent
    buttonTintList = ColorStateList.valueOf(context.getColor(checkColor))
}
