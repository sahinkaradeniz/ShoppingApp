package com.skapps.shoppingapp.ui.products

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.databinding.FragmentProductFeaturesBinding
import com.skapps.shoppingapp.databinding.FragmentProductsBinding
import com.skapps.shoppingapp.ui.products.productsAdapter.ProductsAdapter

class ProductsFragment : Fragment() {
    private lateinit var viewModel: ProductsViewModel
    private lateinit var binding:FragmentProductsBinding
    private lateinit var adapter:ProductsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding= FragmentProductsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
    }

    private fun observeLiveData(){
        viewModel.products.observe(viewLifecycleOwner){ category ->
            binding.toolbarTitleProducts.text=category.categoryName
            adapter= ProductsAdapter(category.products){ product, click ->

            }
            binding.productsRcv.adapter=adapter

        }
    }
}