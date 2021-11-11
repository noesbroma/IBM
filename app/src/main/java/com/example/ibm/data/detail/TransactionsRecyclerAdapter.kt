package com.example.ibm.data.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ibm.R
import com.example.ibm.domain.Transaction
import kotlinx.android.synthetic.main.transaction_item_row.view.*
import java.util.*


class TransactionsRecyclerAdapter(
    var transactions: ArrayList<Transaction>
): RecyclerView.Adapter<TransactionsRecyclerAdapter.TransactionHolder>(){


    private var clickListener: ClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_item_row, parent, false)

        return TransactionHolder(v)
    }


    override fun getItemCount(): Int {
        return transactions.size
    }


    fun getItemName(position: Int): String? {
        return transactions?.get(position).sku
    }


   /* fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }*/


    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val data = transactions?.get(position)
        data?.let { holder.bindItems(it) }
    }


    inner class TransactionHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            if (clickListener != null) {
                itemView.setOnClickListener(this)
            }
        }


        fun bindItems(transaction: Transaction) {
            itemView.sku.text = transaction.sku
            itemView.amount.text = transaction.amount
            itemView.currency.text = transaction.currency
            itemView.euros.text = transaction.euroCurrency.toString() + " EUR"
        }


        override fun onClick(v: View?) {
            if (v != null) {
                clickListener?.onItemClick(v,adapterPosition)
            }
        }
    }


   interface ClickListener {
        fun onItemClick(v: View,position: Int)
    }
}