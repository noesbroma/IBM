package com.example.ibm

import android.app.Application
import com.example.ibm.domain.Transaction
import com.example.ibm.di.detailModule
import com.example.ibm.di.mainModule
import com.example.ibm.domain.Rate
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IBMApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startDependencyInjection()
    }


    private fun startDependencyInjection() {
        startKoin {
            androidContext(this@IBMApplication)
            modules(
                listOf(
                        mainModule,
                        detailModule
                )
            )
        }
    }


    companion object {
    }
}