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

class CartFragment : Fragment() {

    private lateinit var binding:FragmentCartBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var cartBasketAdapter: CartBasketAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentCartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        observeLiveData()
        binding.lifecycleOwner=this
        viewModel.getList(requireContext())
    }
    fun observeLiveData(){
        viewModel.basketLis.observe(viewLifecycleOwner){
            binding.rcvCartBasket.apply {
                cartBasketAdapter= CartBasketAdapter(it)
                adapter=cartBasketAdapter
                layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }
        }
    }

}