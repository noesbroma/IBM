package com.example.ibm.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ibm.MainActivity
import com.example.ibm.R
import com.example.ibm.data.main.Transaction
import com.example.ibm.data.main.TransactionsRecyclerAdapter
import com.example.ibm.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance(): MainFragment {
            val fragment = MainFragment()

            return fragment
        }
    }

    private val viewModel: MainViewModel by viewModel()
    private lateinit var linearLayoutManager: GridLayoutManager
    private var productsList: ArrayList<Transaction> = ArrayList<Transaction>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRates()
        viewModel.getTransactions()

        observeViewModel()
    }


    private fun observeViewModel() {
        viewModel.onLoadSKUEvent.observe(
                viewLifecycleOwner,
                androidx.lifecycle.Observer { products ->
                    linearLayoutManager = GridLayoutManager(context, 3)
                    transactionsRecycler.layoutManager = linearLayoutManager
                    transactionsRecycler.hasFixedSize()

                    productsList = products

                    val mAdapter = context?.let { TransactionsRecyclerAdapter(productsList) }

                    mAdapter?.setOnItemClickListener(object : TransactionsRecyclerAdapter.ClickListener {
                        override fun onItemClick(v: View, position: Int) {
                            (activity as MainActivity).openFragment(
                                    DetailFragment.newInstance(
                                            products[position]
                                    )
                            )
                        }

                    })

                    if (productsList.size > 0) {
                        transactionsRecycler.adapter?.notifyDataSetChanged()
                        transactionsRecycler.adapter = mAdapter
                    } else {
                        //noResults.visibility = View.VISIBLE
                    }
                })
    }
}