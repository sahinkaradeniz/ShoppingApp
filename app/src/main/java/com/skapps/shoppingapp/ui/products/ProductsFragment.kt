package com.skapps.shoppingapp.ui.products

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.databinding.FragmentProductFeaturesBinding
import com.skapps.shoppingapp.databinding.FragmentProductsBinding
import com.skapps.shoppingapp.ui.products.productsAdapter.ProductsAdapter
import com.skapps.shoppingapp.utils.customView.enums.FavoriteClickType
import com.skapps.shoppingapp.utils.succesToast
import com.skapps.shoppingapp.utils.toast

class ProductsFragment : Fragment() {
    private lateinit var viewModel: ProductsViewModel
    private lateinit var binding:FragmentProductsBinding
    private lateinit var adapter:ProductsAdapter
    var categoryId=3
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
        val bundle = this.arguments
        if (bundle != null) {
            categoryId = bundle.getInt("cat", 3)
            viewModel.getCategoryProducts(categoryId)
        }
        binding.backProfile.setOnClickListener {
            findNavController().popBackStack()
        }
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.products.observe(viewLifecycleOwner){ category ->
            binding.toolbarTitleProducts.text=category.categoryName
            adapter= ProductsAdapter(category.products){ product, click ->
                when (click) {
                    FavoriteClickType.FAVORİTE -> {
                        viewModel.addFavoriteProduct(product,requireContext())
                    }
                    FavoriteClickType.BUY -> {
                        viewModel.addBasketProduct(product,requireContext())
                    }
                    FavoriteClickType.PROCUDT ->{
                        val bundle = Bundle()
                        bundle.putSerializable("prod", product.id)
                        Navigation.findNavController(binding.root)
                            .navigate(R.id.action_productsFragment_to_productDetailsFragment,
                                bundle)
                    }
                    else -> {
                        val bundle = Bundle()
                        bundle.putSerializable("prod", product.id)
                        Navigation.findNavController(binding.root)
                            .navigate(R.id.action_productsFragment_to_productDetailsFragment,
                                bundle)
                    }
                }
            }
            binding.productsRcv.adapter=adapter
            binding.productsRcv.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
}