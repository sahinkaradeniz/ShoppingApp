package com.skapps.shoppingapp.ui.wallet

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.databinding.FragmentWalletBinding
import com.skapps.shoppingapp.ui.home.HomeViewModel
import com.skapps.shoppingapp.utils.convertPricetoTL
import com.skapps.shoppingapp.utils.succesAlert
import com.skapps.shoppingapp.utils.warningToast

class WalletDialogFragment : DialogFragment() {

    private lateinit var viewModel: WalletViewModel
    private lateinit var binding:FragmentWalletBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=FragmentWalletBinding.inflate(layoutInflater)
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(WalletViewModel::class.java)
        binding.closeWallet.setOnClickListener {
           dismiss()
        }
        binding.addedMoneyButton.setOnClickListener {
           val money= binding.textInputLayout2.editText?.text.toString()
            if (money.isNotEmpty()){
                viewModel.customer.value?.let { it1 -> viewModel.addedMoneyCustomer(money.toInt()) }
            }else{
                requireContext().warningToast("Lütfen Miktar giriniz")
            }
        }
        observeLiveData()
        super.onViewCreated(view, savedInstanceState)
    }
    private fun observeLiveData(){
        viewModel.moneyStatus.observe(viewLifecycleOwner){
            if (it){
                requireContext().succesAlert("Yükleme işlemi tamamlandı","Tamam")
                binding.textInputLayout2.editText?.text?.clear()
            }
        }
        viewModel.customer.observe(viewLifecycleOwner){
            binding.moneyText.text=it.budget?.convertPricetoTL()
        }
    }
}