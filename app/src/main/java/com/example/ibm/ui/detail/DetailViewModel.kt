package com.example.ibm.ui.detail

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ibm.IBMApplication
import com.example.ibm.domain.Rate
import com.example.ibm.domain.Transaction
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DetailViewModel() : ViewModel() {
    lateinit var transaction: Transaction
    val onGetTransactionsByProductEvent = MutableLiveData<ArrayList<Transaction>>()

    lateinit var transactionsList: ArrayList<Transaction>
    lateinit var ratesList: ArrayList<Rate>
    var totalAmount: Double = 0.0
    var roundedTotalAmount: String = ""
    private val numberFormatter = NumberFormat.getNumberInstance(Locale("es", "ES"))

    fun fetchIntentData(arguments: Bundle) {
        transaction = arguments?.getSerializable("EXTRA_TRANSACTION") as Transaction
        transactionsList = arguments?.getSerializable("EXTRA_TRANSACTIONS_LIST") as ArrayList<Transaction>
        ratesList = arguments?.getSerializable("EXTRA_RATES_LIST") as ArrayList<Rate>
    }


    fun getTransactions() {
        var euroAmount = 0.0

        //transactionsList = IBMApplication.transactions.clone() as ArrayList<Transaction>
        transactionsList = transactionsList.filter { it.sku == transaction.sku } as ArrayList<Transaction>

        for (transaction in transactionsList) {
            if (transaction.currency == "EUR") {
                euroAmount = transaction.amount.toDouble()
            } else {
                //var rateItem = IBMApplication.rateList.filter { it.from == transaction.currency && it.to == "EUR" }
                var rateItem = ratesList.filter { it.from == transaction.currency && it.to == "EUR" }

                if (rateItem.isNotEmpty()) {
                    euroAmount = transaction.amount.toDouble() * rateItem[0].rate.toDouble()
                } else {
                    var rates = ratesList.filter { it.from == transaction.currency}

                    if (rates.isNotEmpty()){
                        for (rate in rates) {
                            var r = ratesList.filter { it.from == rate.to && it.to == "EUR" }

                            if (r.isNotEmpty()) {
                                euroAmount = transaction.amount.toDouble() * r[0].rate.toDouble()
                                break
                            }
                        }
                    }
                }
            }

            totalAmount += euroAmount
            transactionsList[transactionsList.indexOf(transaction)].euroCurrency = formatPrice(euroAmount)
        }

        roundedTotalAmount = formatPrice(totalAmount)

        onGetTransactionsByProductEvent.value = transactionsList
    }


    fun formatPrice(price: Double): String {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.HALF_EVEN
        roundedTotalAmount = df.format(price)

        return numberFormatter.format(df.format(price).replace(",", ".").toDouble())
    }
}