package com.skapps.shoppingapp.ui.commentDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.adapter.ProductCommentAdapter
import com.skapps.shoppingapp.databinding.FragmentCommentDetailsBinding

class CommentDetailsFragment : Fragment() {
    private lateinit var binding:FragmentCommentDetailsBinding
    private lateinit var viewModel: CommentDetailsViewModel
    private lateinit var rcvAdapter:ProductCommentAdapter
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
        viewModel.commentList.observe(viewLifecycleOwner){
            binding.rcvCommentDetails.apply {
                rcvAdapter= ProductCommentAdapter(0,it){ comment ->
                    Toast.makeText(requireContext(),"Tık", Toast.LENGTH_SHORT).show()
                }
                adapter=rcvAdapter
                layoutManager=
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
            }
        }
    }

}