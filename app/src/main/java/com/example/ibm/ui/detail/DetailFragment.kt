package com.example.ibm.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ibm.R
import com.example.ibm.data.main.Transaction
import com.example.ibm.data.main.TransactionsRecyclerAdapter
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.main_fragment.*
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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { viewModel.fetchIntentData(it) }

        setUI()
    }


    private fun setUI() {
        productName.text = viewModel.transaction.sku
    }
}