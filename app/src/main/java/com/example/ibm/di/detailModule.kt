package com.example.ibm.di

import com.example.ibm.IBMRetrofitBuilder
import com.example.ibm.data.main.QuietStoneApiService
import com.example.ibm.data.main.QuietStoneRepository
import com.example.ibm.ui.detail.DetailViewModel
import com.example.ibm.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    viewModel {
        DetailViewModel()
    }
}