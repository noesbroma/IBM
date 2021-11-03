package com.example.ibm.data.main

sealed class GetTransactionsListResult {
    data class Ok(val getTransactionsListResponse: ArrayList<Transaction>) : GetTransactionsListResult()
    object Error: GetTransactionsListResult()
}