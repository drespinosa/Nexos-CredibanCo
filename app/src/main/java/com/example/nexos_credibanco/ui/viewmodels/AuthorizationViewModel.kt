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

    fun getAuthorizationTransaction(authorizationCode: String, authorizationVo: AuthorizationVo) {
        viewModelScope.launch() {
            repository.authorizeTransaction(authorizationCode, authorizationVo)
        }
    }

    fun fetchTransactions(){
        viewModelScope.launch() {
            transactions.value = repository.getTransactions()
        }
    }

    fun fetchFilterTransactions(){
        viewModelScope.launch() {
            transactions.value = repository.getTransactions().filter { it.enable }
        }
    }

    fun getCancelTransaction(authorizationCode: String, cancelVo: CancelVo) {
        viewModelScope.launch() {
            repository.cancelTransaction(authorizationCode, cancelVo)
        }
    }

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