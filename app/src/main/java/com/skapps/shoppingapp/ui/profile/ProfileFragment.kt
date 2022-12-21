package com.skapps.shoppingapp.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.databinding.FragmentProfileBinding
import com.skapps.shoppingapp.utils.convertPricetoTL
import com.skapps.shoppingapp.utils.warningAlert

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
        viewModel.getCustomer(1)
        clickFragment()
        super.onViewCreated(view, savedInstanceState)
    }


    private fun observeLiveData(){
        viewModel.customer.observe(viewLifecycleOwner){ costumer ->
            binding.settingsname.text=costumer.name
            binding.settingsmail.text=costumer.email
            binding.profilePrice.text= costumer.budget!!.convertPricetoTL()
        }
    }

    private fun clickFragment(){
        binding.settingsButtonProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_addAPIFragment)
        }
        binding.backProfile.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.logoutButton.setOnClickListener {
            SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Çıkış yapmaya emin misiniz ?")
                .setConfirmText("Çıkış")
                .setConfirmClickListener {findNavController().popBackStack()}
                .show()
        }
        binding.moneyButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_walletDialogFragment)
        }
        binding.historyButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_historyPurchaseFragment)
        }
    }




}