package com.example.ibm.data.main

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Transaction(
    @SerializedName("sku") val sku: String,
    @SerializedName("amount") val amount: String,
    @SerializedName("currency") val currency: String
): Serializable