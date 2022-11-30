package com.skapps.shoppingapp.ui.productFeatures

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.databinding.FragmentProductFeaturesBinding

class ProductFeaturesFragment : Fragment() {
    private lateinit var binding:FragmentProductFeaturesBinding
    private lateinit var viewModel: ProductFeaturesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentProductFeaturesBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductFeaturesViewModel::class.java)
        binding.backProductFeatures.setOnClickListener{
            findNavController().popBackStack()
        }
    }

}