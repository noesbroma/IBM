package com.example.ibm.data.main

import com.example.ibm.Rate
import com.google.gson.annotations.SerializedName


data class GetRatesResponse(
        @SerializedName("results") val results: ArrayList<Rate>
)
