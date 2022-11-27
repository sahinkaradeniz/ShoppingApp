package com.skapps.shoppingapp.ui.category

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.ui.category.adapter.CategoryLeftAdapter
import com.skapps.shoppingapp.ui.category.adapter.CategoryRightAdapter
import com.skapps.shoppingapp.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
    private lateinit var viewModel: CategoryViewModel
    private lateinit var binding:FragmentCategoryBinding
    private lateinit var categoryLeftAdapter: CategoryLeftAdapter
    private lateinit var categoryRightAdapter: CategoryRightAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentCategoryBinding.inflate(layoutInflater,container,false)
        binding.editTextCategory.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                findNavController().navigate(R.id.action_categoryFragment_to_searchFragment)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        binding.categoryViewModel=viewModel
    }
    private fun observeLiveData(){
        viewModel.categoryList.observe(viewLifecycleOwner){
            binding.rightRcvCategory.apply {
                categoryRightAdapter= CategoryRightAdapter(it)
                adapter=categoryRightAdapter
                layoutManager=GridLayoutManager(requireContext(),2)
                setHasFixedSize(true)
            }
            binding.leftRcvCategory.apply {
                categoryLeftAdapter= CategoryLeftAdapter(it)
                adapter=categoryLeftAdapter
                layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                setHasFixedSize(true)
            }
        }
    }

}