package com.skapps.shoppingapp.ui.productdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.ui.productdetails.adapter.ProductCommentAdapter
import com.skapps.shoppingapp.data.remote.status.ApiStatus
import com.skapps.shoppingapp.databinding.FragmentProductDetailsBinding
import com.skapps.shoppingapp.utils.*


class ProductDetailsFragment : Fragment() {
    private var productId:Int=0
    private lateinit var viewModel: ProductDetailsViewModel
    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var rcvAdapter: ProductCommentAdapter

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
            productId = bundle.getInt("prod", )
            viewModel.getProduct(productId)
        }
        clickFragment()
    }

    private fun observeLiveData(){
        viewModel.commentList.observe(viewLifecycleOwner){
            binding.rcvProductComment.apply {
                rcvAdapter= ProductCommentAdapter(1,it){ comment ->
                //    Toast.makeText(requireContext(),"Tık",Toast.LENGTH_SHORT).show()
                }
                adapter=rcvAdapter
                layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
                binding.productCommentQuantitiy.text= "${it.size} Değerlendirme"
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
        viewModel.status.observe(viewLifecycleOwner){ status ->
            when(status){
                ApiStatus.LOADING ->{
                    binding.progressDetails.show()
                    binding.contrailBottom.hide()
                    binding.scrollView3.hide()
                }
                ApiStatus.DONE -> {
                    binding.progressDetails.hide()
                    binding.contrailBottom.show()
                    binding.scrollView3.show()
                }
                ApiStatus.ERROR -> {
                    binding.progressDetails.hide()
                    binding.contrailBottom.hide()
                    binding.scrollView3.hide()
                    requireContext().warningAlert("Bir sorun oluştu internet ayarlarınızı kontrol ediniz.","Tamam")
                    findNavController().popBackStack()
                }
            }
        }
    }
    private fun clickFragment(){
        binding.commentDetailsProduct.setOnClickListener {
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
            viewModel.addBasketProduct(requireContext())
            requireContext().succesBasketToast()
        }
        binding.addFavoriteButton.setOnClickListener {
            viewModel.addFavoriteProdut(requireContext())
            requireContext().succesFavoriteToast()
        }
    }

}