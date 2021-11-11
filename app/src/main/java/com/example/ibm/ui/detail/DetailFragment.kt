package com.example.ibm.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ibm.R
import com.example.ibm.data.detail.TransactionsRecyclerAdapter
import com.example.ibm.data.main.Transaction
import kotlinx.android.synthetic.main.detail_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(transaction: Transaction): DetailFragment {
            val fragment = DetailFragment()

            val args = Bundle()
            args.putSerializable("EXTRA_TRANSACTION", transaction)
            fragment.arguments = args

            return fragment
        }
    }

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var transactionsList: ArrayList<Transaction> = ArrayList<Transaction>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { viewModel.fetchIntentData(it) }

        viewModel.getTransactions()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setUI()
        observeViewModel()
    }


    private fun setUI() {
        productName.text = String.format("%s: %s", resources.getString(R.string.product), viewModel.transaction.sku)


    }

    private fun observeViewModel() {
       viewModel.onGetTransactionsByProductEvent.observe(
                viewLifecycleOwner,
                androidx.lifecycle.Observer { transactions ->
                    linearLayoutManager = LinearLayoutManager(context)
                    transactionsRecycler.layoutManager = linearLayoutManager
                    transactionsRecycler.hasFixedSize()

                    transactionsList = transactions

                    val mAdapter = context?.let { TransactionsRecyclerAdapter(transactionsList) }

                    if (transactionsList.size > 0) {
                        transactionsRecycler.adapter?.notifyDataSetChanged()
                        transactionsRecycler.adapter = mAdapter
                    } else {
                        //noResults.visibility = View.VISIBLE
                    }

                    totalAmount.text = String.format("%s: %s EUR", resources.getString(R.string.total), viewModel.roundedTotalAmount)
                })
    }
}