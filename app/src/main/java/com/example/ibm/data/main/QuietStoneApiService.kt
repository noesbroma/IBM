package com.example.ibm.data.main

import com.example.ibm.Rate
import retrofit2.http.GET
import retrofit2.http.Query

interface QuietStoneApiService {

    @GET("transactions.json")
    suspend fun getTransactionsList(): ArrayList<Transaction>?


    @GET("rates.json")
    suspend fun getRates(): ArrayList<Rate>?
}