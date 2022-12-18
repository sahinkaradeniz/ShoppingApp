package com.skapps.shoppingapp.ui.purchaseHistory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.databinding.FragmentHistoryPurchaseBinding
import com.skapps.shoppingapp.ui.purchaseHistory.adapter.HistoryParentAdapter

class HistoryPurchaseFragment : Fragment() {

    private lateinit var viewModel: HistoryPurchaseViewModel
    private lateinit var binding:FragmentHistoryPurchaseBinding
   private lateinit var adapter: HistoryParentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding=FragmentHistoryPurchaseBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HistoryPurchaseViewModel::class.java)
        observeLiveData()
        binding.backCartFragment2.setOnClickListener {
            findNavController().popBackStack()
        }
    }
   private fun observeLiveData(){
        viewModel.purchaseHistory.observe(viewLifecycleOwner){
            adapter=HistoryParentAdapter(it)
            binding.rcvPurchasesHistory.adapter=adapter
            binding.rcvPurchasesHistory.layoutManager= LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        }
    }



}