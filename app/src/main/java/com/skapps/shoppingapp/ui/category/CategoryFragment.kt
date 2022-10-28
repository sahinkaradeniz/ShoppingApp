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
import com.skapps.shoppingapp.adapter.CategoryLeftAdapter
import com.skapps.shoppingapp.adapter.CategoryRightAdapter
import com.skapps.shoppingapp.databinding.FragmentCategoryBinding
import com.skapps.shoppingapp.model.Category

class CategoryFragment : Fragment() {
    private lateinit var viewModel: CategoryViewModel
    private lateinit var binding:FragmentCategoryBinding
    private lateinit var categoryLeftAdapter:CategoryLeftAdapter
    private lateinit var categoryRightAdapter: CategoryRightAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentCategoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val category=Category("1","Outdoor Ayakkabı","null")
        val clist=ArrayList<Category>()
        clist.add(category)
        clist.add(category)
        clist.add(category)
        clist.add(category)
        clist.add(category)
        clist.add(category)
        clist.add(category)
        clist.add(category)
        viewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        binding.leftRcvCategory.apply {
            categoryLeftAdapter= CategoryLeftAdapter(clist)
            adapter=categoryLeftAdapter
            layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
        }
        binding.rightRcvCategory.apply {
            categoryRightAdapter= CategoryRightAdapter(clist)
            adapter=categoryRightAdapter
            layoutManager=GridLayoutManager(requireContext(),2)
            setHasFixedSize(true)
        }
        binding.editTextCategory.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                findNavController().navigate(R.id.action_categoryFragment_to_searchFragment)
            }
        }
    }

}