package com.example.nexos_credibanco.ui.interfaces

interface ICanceItem<T : Any> {

    /**
     * Método que informa cuando se cancela una transacción.
     */
    fun onCancelClick(item: T)

}