package com.skapps.shoppingapp.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.adapter.ProductCommentAdapter
import com.skapps.shoppingapp.databinding.FragmentProductDetailsBinding
import com.skapps.shoppingapp.model.Product

class ProductDetailsFragment : Fragment() {
    private lateinit var product:Product
    private lateinit var viewModel: ProductDetailsViewModel
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var rcvAdapter:ProductCommentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View{
        product= requireArguments().get("product") as Product
        binding=FragmentProductDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
        viewModel.setProduct(product)
        observeLiveData()
        binding.productCommentText.setOnClickListener {
            val bundle=Bundle()
            bundle.putSerializable("productid",product.name)
            findNavController().navigate(R.id.action_productDetailsFragment_to_commentDetailsFragment,bundle)
        }
        binding.productCommentText2.setOnClickListener {
            val bundle=Bundle()
            bundle.putSerializable("productid",product.name)
            findNavController().navigate(R.id.action_productDetailsFragment_to_commentDetailsFragment,bundle)
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
        viewModel.detailsProduct.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),product.name,Toast.LENGTH_SHORT).show()
        }
    }

}