package com.example.ibm

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class IBMRetrofitBuilder {
    companion object {
        private var retrofit_api: Retrofit? = null


        fun getRetrofitApi(): Retrofit? {
            if (retrofit_api == null) initRetrofitApi()

            return retrofit_api
        }


        private fun initRetrofitApi() {
            retrofit_api = Retrofit.Builder()
                .baseUrl(IBMApplication.baseSecureHost)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}