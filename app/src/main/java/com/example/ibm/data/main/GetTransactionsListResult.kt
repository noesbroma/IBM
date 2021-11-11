package com.example.ibm.data.main

import com.example.ibm.domain.Transaction

sealed class GetTransactionsListResult {
    data class Ok(val getTransactionsListResponse: ArrayList<Transaction>) : GetTransactionsListResult()
    object Error: GetTransactionsListResult()
}