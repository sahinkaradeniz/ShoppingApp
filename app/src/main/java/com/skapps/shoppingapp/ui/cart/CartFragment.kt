package com.skapps.shoppingapp.ui.cart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.adapter.CartBasketAdapter
import com.skapps.shoppingapp.databinding.FragmentCartBinding
import com.skapps.shoppingapp.databinding.RowCartDesignBinding
import com.skapps.shoppingapp.model.Basket
import com.skapps.shoppingapp.model.Product

class CartFragment : Fragment() {

    private lateinit var binding:FragmentCartBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var cartBasketAdapter: CartBasketAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        val product= Product(getString(R.string.test_string_ayakkabi),"Harley Davidson","299.99",4.6)
        val basket=Basket("id",product)
        val basketList=ArrayList<Basket>()
            basketList.add(basket)
            basketList.add(basket)
            basketList.add(basket)
            basketList.add(basket)
            basketList.add(basket)
            basketList.add(basket)
            basketList.add(basket)
        binding.rcvCartBasket.apply {
            cartBasketAdapter=CartBasketAdapter(basketList)
            adapter=cartBasketAdapter
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
    }

}