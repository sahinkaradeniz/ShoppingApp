package com.skapps.shoppingapp.ui.commentDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.data.remote.status.ApiStatus
import com.skapps.shoppingapp.ui.productdetails.adapter.ProductCommentAdapter
import com.skapps.shoppingapp.databinding.FragmentCommentDetailsBinding
import com.skapps.shoppingapp.utils.hide
import com.skapps.shoppingapp.utils.show
import com.skapps.shoppingapp.utils.warningAlert

class CommentDetailsFragment : Fragment() {
    private lateinit var binding:FragmentCommentDetailsBinding
    private lateinit var viewModel: CommentDetailsViewModel
    private lateinit var rcvAdapter: ProductCommentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentCommentDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommentDetailsViewModel::class.java)
        val bundle = this.arguments
        if (bundle != null) {
           val productId = bundle.getInt("product")
            viewModel.getAllCommentProduct(productId)
        }
        observeLiveData()
        clickFragment()
    }
    private fun observeLiveData(){
        viewModel.commentList.observe(viewLifecycleOwner){
            binding.rcvCommentDetails.apply {
                rcvAdapter= ProductCommentAdapter(0,it){ comment ->
                }
                adapter=rcvAdapter
                layoutManager=
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
            }
        }
        viewModel.status.observe(viewLifecycleOwner){ status ->
            when(status){
                ApiStatus.LOADING ->{
                    binding.progressCommentDetails.show()
                }
                ApiStatus.DONE->{
                    binding.progressCommentDetails.hide()
                }
                ApiStatus.ERROR->{
                    requireContext().warningAlert("Bir Sorun Oluştu","Tamam")
                    findNavController().popBackStack()
                }
            }
        }
    }
    private fun clickFragment(){
        binding.backButtonComments.setOnClickListener{
            findNavController().popBackStack()
        }
    }

}