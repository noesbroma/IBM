package com.example.ibm.data.main

import com.example.ibm.domain.Rate

sealed class GetRatesResult {
    data class Ok(val getRatesResponse: ArrayList<Rate>) : GetRatesResult()
    object Error: GetRatesResult()
}