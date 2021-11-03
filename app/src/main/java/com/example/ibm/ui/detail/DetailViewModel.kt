package com.example.ibm.ui.detail

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ibm.IBMApplication
import com.example.ibm.data.main.GetRatesResult
import com.example.ibm.data.main.GetTransactionsListResult
import com.example.ibm.data.main.QuietStoneRepository
import com.example.ibm.data.main.Transaction
import kotlinx.coroutines.launch

class DetailViewModel() : ViewModel() {
    lateinit var transaction: Transaction

    fun fetchIntentData(arguments: Bundle) {
        transaction = arguments?.getSerializable("EXTRA_TRANSACTION") as Transaction
    }
}