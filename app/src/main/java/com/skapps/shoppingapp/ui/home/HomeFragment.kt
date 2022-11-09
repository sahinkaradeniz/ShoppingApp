package com.skapps.shoppingapp.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.adapter.HomeParentRcvAdapter
import com.skapps.shoppingapp.databinding.FragmentHomeBinding
import com.skapps.shoppingapp.model.Product
import com.skapps.shoppingapp.model.ProductModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding:FragmentHomeBinding
    private lateinit var homeParentRcvAdapter: HomeParentRcvAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner=this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.editText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }
        }
        binding.textInputLayout.setStartIconOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
        viewModel.products.observe(viewLifecycleOwner){
            binding.rcvHome.apply {
                homeParentRcvAdapter=HomeParentRcvAdapter(it){ product ->
                    val bundle=Bundle()
                    bundle.putSerializable("product",product)
                    findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment,bundle)
                }
                adapter=homeParentRcvAdapter
                layoutManager=LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL,false)
            }
        }

    }

}