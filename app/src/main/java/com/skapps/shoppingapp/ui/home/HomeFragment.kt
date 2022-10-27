package com.skapps.shoppingapp.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.database.HomeParentRcvAdapter
import com.skapps.shoppingapp.databinding.FragmentHomeBinding
import com.skapps.shoppingapp.model.Product
import com.skapps.shoppingapp.model.ProductModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding:FragmentHomeBinding
    private lateinit var homeParentRcvAdapter:HomeParentRcvAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentHomeBinding.inflate(inflater)
        val product=Product(getString(R.string.test_string_ayakkabi),"299.99",4.6)
        val productList=ArrayList<Product>()
        productList.add(product)
        productList.add(product)
        productList.add(product)
        productList.add(product)
        productList.add(product)
        productList.add(product)
        productList.add(product)
        val productModel=ProductModel(2,"Ayakkabılar",productList)
        val productModelList=ArrayList<ProductModel>()
        productModelList.add(productModel)
        productModelList.add(productModel)
        productModelList.add(productModel)
        productModelList.add(productModel)
        homeParentRcvAdapter=HomeParentRcvAdapter(productModelList)
        binding.rcvHome.layoutManager= LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL,false)
        binding.rcvHome.adapter=homeParentRcvAdapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

    }

}