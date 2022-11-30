package com.skapps.shoppingapp.ui.productDescription

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.databinding.FragmentProductDescriptionBinding

class ProductDescriptionFragment : Fragment() {

    private lateinit var viewModel: FragmentProductDescriptionBinding
    private lateinit var binding:FragmentProductDescriptionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentProductDescriptionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backPoductDescription.setOnClickListener {
            findNavController().popBackStack()
        }
        super.onViewCreated(view, savedInstanceState)
    }


}