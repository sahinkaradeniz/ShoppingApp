package com.skapps.shoppingapp.ui.commentToProduct

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.data.remote.status.ApiStatus
import com.skapps.shoppingapp.databinding.FragmentCommentProductBinding
import com.skapps.shoppingapp.utils.*

class CommentProductFragment : Fragment() {

    private lateinit var binding:FragmentCommentProductBinding
    private lateinit var viewModel: CommentProductViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=FragmentCommentProductBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommentProductViewModel::class.java)
        val bundle = this.arguments
        if (bundle != null) {
            val productId = bundle.getInt("product")
            viewModel.getProduct(productId)
        }
       fragmentClick()
        observeLiveData()
    }
    private fun fragmentClick(){
        binding.addCommentCutton.setOnClickListener {
            addCommentProduct()
        }
        binding.backButtonComments.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun addCommentProduct(){
      val text=binding.commentProductText.editText?.text.toString()
      val rating =binding.ratingBarComment.rating.toDouble()
      viewModel.addComment(rating,text)
    }
    private fun observeLiveData(){
        viewModel.status.observe(viewLifecycleOwner){ apiStatus ->
            when(apiStatus){
                ApiStatus.LOADING -> {
                    binding.scrollView2.hide()
                    binding.progress.show()
                }
                ApiStatus.ERROR -> {
                    binding.scrollView2.hide()
                    requireContext().warningAlert("Bir sorun oluştu ","Tamam")
                    binding.progress.hide()
                }
                ApiStatus.DONE -> {
                    binding.progress.hide()
                    binding.scrollView2.show()
                    requireContext().succesAlert("Yourumunuz Kaydedildi","Tamam")
                    findNavController().popBackStack()
                }
            }
        }
        viewModel.product.observe(viewLifecycleOwner){ product ->
            try {
                binding.imageProductComment.downloadImage(product.imageUuid)
                binding.prodcutNameComment.text=product.description
            }catch (e:Exception){
                Log.e("Comment Product Fragment",e.message.toString())
            }
        }
    }


}