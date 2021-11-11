package com.example.ibm.data.main

import com.example.ibm.domain.Rate
import com.example.ibm.domain.Transaction
import retrofit2.http.GET

interface QuietStoneApiService {

    @GET("transactions.json")
    suspend fun getTransactionsList(): ArrayList<Transaction>?


    @GET("rates.json")
    suspend fun getRates(): ArrayList<Rate>?
}