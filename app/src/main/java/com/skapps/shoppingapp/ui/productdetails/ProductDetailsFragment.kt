package com.skapps.shoppingapp.ui.productdetails

import android.R.attr.defaultValue
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.adapter.ProductCommentAdapter
import com.skapps.shoppingapp.databinding.FragmentProductDetailsBinding
import com.skapps.shoppingapp.utils.downloadImage
import com.skapps.shoppingapp.utils.succesToast


class ProductDetailsFragment : Fragment() {
    private var productId:Int=0
    private lateinit var viewModel: ProductDetailsViewModel
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var rcvAdapter:ProductCommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ):View{
        binding=FragmentProductDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
        observeLiveData()
        val bundle = this.arguments
        if (bundle != null) {
            productId = bundle.getInt("prod", defaultValue)
        }
        viewModel.getProduct(productId)
        binding.productCommentText2.setOnClickListener {
          //     val bundle=Bundle()
         //      bundle.putSerializable("productid",product.name)
        //     findNavController().navigate(R.id.action_productDetailsFragment_to_commentDetailsFragment,bundle)
        }
        binding.productFeatures.setOnClickListener {
            findNavController().navigate(R.id.action_productDetailsFragment_to_productFeaturesFragment)
        }
        binding.backButtonDetails.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.productDescription.setOnClickListener {
            findNavController().navigate(R.id.action_productDetailsFragment_to_productDescriptionFragment)
        }
        binding.addBasketButton.setOnClickListener{
            requireContext().succesToast("Sepete Eklendi")
        }

    }

    private fun observeLiveData(){
        viewModel.commentList.observe(viewLifecycleOwner){
            binding.rcvProductComment.apply {
                rcvAdapter=ProductCommentAdapter(1,it){ comment ->
                    Toast.makeText(requireContext(),"Tık",Toast.LENGTH_SHORT).show()
                }
                adapter=rcvAdapter
                layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
            }
        }
        viewModel.detailsProduct.observe(viewLifecycleOwner){ product ->
            binding.apply {
                productDtailImage.downloadImage(product.imageUuid)
                productBrand.text=product.brand
                productModel.text=product.model
                productDestcription.text=product.description
                productRatingText.text=product.averageRating.toString()
            }
        }
    }

}