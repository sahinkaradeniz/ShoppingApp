package com.skapps.shoppingapp.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding:FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        observeLiveData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun observeLiveData(){
        viewModel.customer.observe(viewLifecycleOwner){ costumer ->
            binding.settingsname.text=costumer.name
            binding.settingsmail.text=costumer.email
        }
    }




}