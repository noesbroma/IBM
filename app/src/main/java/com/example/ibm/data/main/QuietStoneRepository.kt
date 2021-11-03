package com.example.ibm.data.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuietStoneRepository(val quietStoneApiService: QuietStoneApiService) {
    suspend fun getList(): GetTransactionsListResult {
        return withContext(Dispatchers.IO) {
            try {
                val response = quietStoneApiService.getTransactionsList()

                if (response != null) {
                    GetTransactionsListResult.Ok(response)
                } else {
                    GetTransactionsListResult.Error
                }
            } catch (e: Throwable) {
                GetTransactionsListResult.Error
            }
        }
    }


    suspend fun getRates(): GetRatesResult {
        return withContext(Dispatchers.IO) {
            try {
                val response = quietStoneApiService.getRates()

                if (response != null) {
                    GetRatesResult.Ok(response)
                } else {
                    GetRatesResult.Error
                }
            } catch (e: Throwable) {
                GetRatesResult.Error
            }
        }
    }
}