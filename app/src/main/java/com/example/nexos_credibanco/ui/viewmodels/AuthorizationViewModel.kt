package com.example.nexos_credibanco.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nexos_credibanco.data.model.AuthorizationResponseVo
import com.example.nexos_credibanco.data.model.AuthorizationVo
import com.example.nexos_credibanco.data.model.CancelVo
import com.example.nexos_credibanco.repository.TransactionRepository
import kotlinx.coroutines.launch

class AuthorizationViewModel(
    private val application: Application,
) : ViewModel() {

    private val repository: TransactionRepository = TransactionRepository(application)

    private val transactions = MutableLiveData<List<AuthorizationResponseVo>>()
    fun getTransactions(): LiveData<List<AuthorizationResponseVo>> = transactions

    private val filteredTransactions = MutableLiveData<List<AuthorizationResponseVo>>()

    /**
     * Método que obtiene la autorizaccion de la transacción
     */
    fun getAuthorizationTransaction(authorizationCode: String, authorizationVo: AuthorizationVo) {
        viewModelScope.launch() {
            repository.authorizeTransaction(authorizationCode, authorizationVo)
        }
    }

    /**
     * Método que actualiza la lista de todas las transacciones
     */
    fun fetchTransactions() {
        viewModelScope.launch() {
            transactions.value = repository.getTransactions()
        }
    }

    /**
     * Método que actualiza la lista de las transacciones autorizadas
     */
    fun fetchFilterTransactions() {
        viewModelScope.launch() {
            transactions.value = repository.getTransactions().filter { it.enable }
        }
    }

    /**
     * Método que obtiene la anulación de la transacción
     */
    fun getCancelTransaction(authorizationCode: String, cancelVo: CancelVo) {
        viewModelScope.launch() {
            repository.cancelTransaction(authorizationCode, cancelVo)
        }
    }

    /**
     * Método que actualiza las transacciones
     */
    fun updateTransactions(transactions: List<AuthorizationResponseVo>) {
        viewModelScope.launch() {
            filteredTransactions.value = transactions.filter { it.enable }
        }
    }


    class AuthorizationViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(AuthorizationViewModel::class.java)) {
                AuthorizationViewModel(application) as T
            } else {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

}