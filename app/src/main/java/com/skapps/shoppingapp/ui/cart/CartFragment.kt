package com.skapps.shoppingapp.ui.cart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.ui.cart.adapter.CartBasketAdapter
import com.skapps.shoppingapp.databinding.FragmentCartBinding
import com.skapps.shoppingapp.utils.CartClickType
import com.skapps.shoppingapp.utils.succesToast

class CartFragment : Fragment() {

    private lateinit var binding:FragmentCartBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var cartBasketAdapter: CartBasketAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentCartBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        observeLiveData()
        binding.lifecycleOwner=this
        viewModel.getList(requireContext())
        return binding.root
    }

    fun observeLiveData(){
        viewModel.basketList.observe(viewLifecycleOwner){
            binding.rcvCartBasket.apply {
                cartBasketAdapter= CartBasketAdapter(it){ product , type ->
                    when(type){
                        CartClickType.ADD -> {requireContext().succesToast("ekle")}
                        CartClickType.REDUCTION ->{requireContext().succesToast("cıkar")}
                        CartClickType.DELETE ->{requireContext().succesToast("sil")}
                        else ->{requireContext().succesToast("ürün")}
                    }
                }
                adapter=cartBasketAdapter
                layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }
        }
    }

}