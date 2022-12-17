package com.skapps.shoppingapp.ui.purchase

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.data.remote.ApiStatus
import com.skapps.shoppingapp.databinding.FragmentPurchaseBinding
import com.skapps.shoppingapp.ui.purchase.adapter.PurhaceProductAdapter
import com.skapps.shoppingapp.utils.convertPricetoTL
import com.skapps.shoppingapp.utils.hide
import com.skapps.shoppingapp.utils.show

class PurchaseFragment : Fragment() {


    private lateinit var viewModel: PurchaseViewModel
    private lateinit var binding: FragmentPurchaseBinding
    private lateinit var pruchaseAdapter: PurhaceProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPurchaseBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PurchaseViewModel::class.java)
        viewModel.getAllBasketList(requireContext())
        observeLiveData()
        binding.backCartFragment.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.basketBuyButton.setOnClickListener {
            viewModel.purchaseProducts()
        }
    }

    private fun hideViewCompanent() {
        binding.cardView7.hide()
        binding.nestedScrollView2.hide()
        binding.erorText.hide()
    }

    private fun showViewCompanent() {
        binding.cardView7.show()
        binding.nestedScrollView2.show()
        binding.progressBarPurhase.hide()
        binding.erorText.hide()
    }

    private fun apiErorViewCompanent() {
        hideViewCompanent()
        binding.erorText.show()
    }

    private fun observeLiveData() {
        viewModel.apiStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                ApiStatus.LOADING -> {
                    hideViewCompanent()
                }
                ApiStatus.ERROR -> {
                    apiErorViewCompanent()
                }
                ApiStatus.DONE -> {
                    showViewCompanent()
                }
            }
        }
        viewModel.productsList.observe(viewLifecycleOwner) {
            pruchaseAdapter = PurhaceProductAdapter(it)
            binding.rcvPurchaseFragment.adapter = pruchaseAdapter
            binding.rcvPurchaseFragment.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        viewModel.productQuantity.observe(viewLifecycleOwner) {
            binding.basketQuntText.text = "Ürünler Toplam ($it)"
        }
        viewModel.totalPrice.observe(viewLifecycleOwner) {
            binding.basketPriceText.text = it.convertPricetoTL()
        }
        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.userNamePurchase.text = user.name
            binding.userPhonePurchase.text = user.phoneNumber
            binding.userBudgetPurchase.text = user.budget?.convertPricetoTL()
        }
    }

}