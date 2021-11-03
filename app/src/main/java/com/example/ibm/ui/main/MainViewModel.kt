package com.example.ibm.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ibm.IBMApplication
import com.example.ibm.data.main.GetRatesResult
import com.example.ibm.data.main.GetTransactionsListResult
import com.example.ibm.data.main.QuietStoneRepository
import com.example.ibm.data.main.Transaction
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuietStoneRepository) : ViewModel() {
    val onLoadSKUEvent = MutableLiveData<ArrayList<Transaction>>()

    fun getTransactions() {
        viewModelScope.launch {
            when (val result = repository.getList()) {
                is GetTransactionsListResult.Ok -> {
                    onLoadSKUEvent.value = result.getTransactionsListResponse.distinctBy { it.sku } as ArrayList<Transaction>
                    IBMApplication.transactions = result.getTransactionsListResponse
                }

                is GetTransactionsListResult.Error -> {
                }
            }
        }
    }


    fun getRates() {
        viewModelScope.launch {
            when (val result = repository.getRates()) {
                is GetRatesResult.Ok -> {
                    IBMApplication.rateList = result.getRatesResponse
                }

                is GetRatesResult.Error -> {
                }
            }
        }
    }
}