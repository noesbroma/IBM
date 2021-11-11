package com.example.ibm.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class IBMRetrofitBuilder {
    companion object {
        private var retrofit_api: Retrofit? = null
        val baseSecureHost = "https://quiet-stone-2094.herokuapp.com/"


        fun getRetrofitApi(): Retrofit? {
            if (retrofit_api == null) initRetrofitApi()

            return retrofit_api
        }


        private fun initRetrofitApi() {
            retrofit_api = Retrofit.Builder()
                .baseUrl(baseSecureHost)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}