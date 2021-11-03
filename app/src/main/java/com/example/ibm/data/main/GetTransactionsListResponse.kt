package com.example.ibm.data.main

import com.google.gson.annotations.SerializedName


data class GetTransactionsListResponse(
        @SerializedName("results") val results: ArrayList<Transaction>
)
