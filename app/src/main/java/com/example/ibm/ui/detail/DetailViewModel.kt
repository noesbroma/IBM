package com.example.ibm.ui.detail

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ibm.IBMApplication
import com.example.ibm.data.main.Transaction
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DetailViewModel() : ViewModel() {
    lateinit var transaction: Transaction
    val onGetTransactionsByProductEvent = MutableLiveData<ArrayList<Transaction>>()
    lateinit var lista: ArrayList<Transaction>
    var totalAmount: Double = 0.0
    var roundedTotalAmount: String = ""
    private val numberFormatter = NumberFormat.getNumberInstance(Locale("es", "ES"))

    fun fetchIntentData(arguments: Bundle) {
        transaction = arguments?.getSerializable("EXTRA_TRANSACTION") as Transaction
    }


    fun getTransactions() {
        var euroAmount = 0.0

        lista = IBMApplication.transactions.clone() as ArrayList<Transaction>
        lista = lista.filter { it.sku == transaction.sku } as ArrayList<Transaction>

        for (transaction in lista) {
            if (transaction.currency == "EUR") {
                euroAmount = transaction.amount.toDouble()
                totalAmount += euroAmount
            } else {
                var rateItem = IBMApplication.rateList.filter { it.from == transaction.currency && it.to == "EUR" }

                if (rateItem.isNotEmpty()) {
                    euroAmount = rateItem[0].rate.toDouble()
                    totalAmount += transaction.amount.toDouble() * euroAmount
                } else {
                    var rates = IBMApplication.rateList.filter { it.from == transaction.currency}

                    if (rates.isNotEmpty()){
                        for (rate in rates) {
                            var r = IBMApplication.rateList.filter { it.from == rate.to && it.to == "EUR" }

                            if (r.isNotEmpty()) {
                                euroAmount = r[0].rate.toDouble()
                                totalAmount += transaction.amount.toDouble() * euroAmount
                                break
                            }
                        }
                    }
                }
            }

            lista[lista.indexOf(transaction)].euroCurrency = euroAmount
        }

        roundedTotalAmount = formatPrice(totalAmount)

        onGetTransactionsByProductEvent.value = lista
    }


    fun formatPrice(price: Double): String {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.HALF_EVEN
        roundedTotalAmount = df.format(price)

        return numberFormatter.format(df.format(price).replace(",", ".").toDouble())
    }
}