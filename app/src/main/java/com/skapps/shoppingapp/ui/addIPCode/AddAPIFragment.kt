package com.skapps.shoppingapp.ui.addIPCode

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.databinding.FragmentAddAPIBinding
import com.skapps.shoppingapp.utils.API
import com.skapps.shoppingapp.utils.warningToast

class AddAPIFragment : Fragment() {


    private lateinit var viewModel: AddAPIViewModel
    private lateinit var binding:FragmentAddAPIBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        binding=FragmentAddAPIBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddAPIViewModel::class.java)
        clickFragment()
        setEditText()
    }
    private fun clickFragment(){
        binding.button.setOnClickListener {
            val ip=binding.ipaddress.editText?.text.toString()
            if (ip.isNotEmpty()){
                viewModel.addIPAdress(ip)
                findNavController().navigate(R.id.action_addAPIFragment_to_homeFragment)
            }else{
                binding.ipaddress.isErrorEnabled=true
                requireContext().warningToast("Lütfen ip giriniz ")
            }

        }
        binding.backButtonApi.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun setEditText(){
        val ip =API.getApi()
        if (ip != "0"){
            binding.ipaddress.hint= ip
        }
    }
}