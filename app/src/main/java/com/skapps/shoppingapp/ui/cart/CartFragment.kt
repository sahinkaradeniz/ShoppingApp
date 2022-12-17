package com.skapps.shoppingapp.ui.cart

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.ui.cart.adapter.CartBasketAdapter
import com.skapps.shoppingapp.databinding.FragmentCartBinding
import com.skapps.shoppingapp.utils.convertPricetoTL
import com.skapps.shoppingapp.utils.customView.enums.CartClickType
import com.skapps.shoppingapp.utils.toast

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var cartBasketAdapter: CartBasketAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        observeLiveData()
        viewModel.getList(requireContext())
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    fun observeLiveData() {
        viewModel.basketList.observe(viewLifecycleOwner) {
            binding.rcvCartBasket.apply {
                cartBasketAdapter = CartBasketAdapter(it) { product, type ->
                    when (type) {
                        CartClickType.ADD -> {
                            viewModel.addProductCart(product, requireContext())
                        }
                        CartClickType.REDUCTION -> {
                            viewModel.minusProduct(product, requireContext())
                        }
                        CartClickType.DELETE -> {
                            viewModel.deleteProduct(product, requireContext())
                        }
                        else -> {
                            val bundle = Bundle()
                            bundle.putSerializable("prod", product.id)
                            Navigation.findNavController(binding.root)
                                .navigate(R.id.action_cartFragment_to_productDetailsFragment,
                                    bundle)
                        }
                    }
                }
                adapter = cartBasketAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }
        viewModel.basketPrice.observe(viewLifecycleOwner){
                binding.basketPriceText.text=it.convertPricetoTL()
        }
        viewModel.basketSize.observe(viewLifecycleOwner){
                binding.basketSizeText.text="Ürünler Toplam ($it)"
        }
        binding.basketBuyButton.setOnClickListener{
            findNavController().navigate(R.id.action_cartFragment_to_purchaseFragment)
        }


    }
}
