package com.example.ibm.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rate(
        @SerializedName("from") val from: String,
        @SerializedName("to") val to: String,
        @SerializedName("rate") val rate: String
): Serializable