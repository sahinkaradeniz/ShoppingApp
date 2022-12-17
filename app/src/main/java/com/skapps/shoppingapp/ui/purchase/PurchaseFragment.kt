package com.skapps.shoppingapp.ui.purchase

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.databinding.FragmentPurchaseBinding

class PurchaseFragment : Fragment() {


    private lateinit var viewModel: PurchaseViewModel
    private lateinit var binding:FragmentPurchaseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentPurchaseBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PurchaseViewModel::class.java)
        binding.button.setOnClickListener {
            viewModel.purchaseProducts()
        }
        viewModel.getAllBasketList(requireContext())
    }

}