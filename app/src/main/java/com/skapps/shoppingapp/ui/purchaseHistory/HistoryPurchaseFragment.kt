package com.skapps.shoppingapp.ui.purchaseHistory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skapps.shoppingapp.R

class HistoryPurchaseFragment : Fragment() {

    companion object {
        fun newInstance() = HistoryPurchaseFragment()
    }

    private lateinit var viewModel: HistoryPurchaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_history_purchase, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HistoryPurchaseViewModel::class.java)
        // TODO: Use the ViewModel
    }

}