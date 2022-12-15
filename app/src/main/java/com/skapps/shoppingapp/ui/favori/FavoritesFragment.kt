package com.skapps.shoppingapp.ui.favori

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.databinding.FragmentFavoriBinding
import com.skapps.shoppingapp.ui.favori.adapter.FavoritesProductAdapter
import com.skapps.shoppingapp.utils.customView.enums.FavoriteClickType
import com.skapps.shoppingapp.utils.succesToast
import com.skapps.shoppingapp.utils.toast

class FavoritesFragment : Fragment() {

    private lateinit var viewModel: FavoritesViewModel
    private lateinit var binding: FragmentFavoriBinding
    private lateinit var adapter: FavoritesProductAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFavoriBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        viewModel.getAllList(requireContext())

        viewModel.favoriteList.observe(viewLifecycleOwner) {
            adapter = FavoritesProductAdapter(it) { product, clickType ->
                when (clickType) {
                    FavoriteClickType.DELETE -> {
                        requireContext().toast("Favorilerden Kaldırıldı")
                        viewModel.delete(product, requireContext())
                    }
                    FavoriteClickType.BUY -> {
                        requireContext().succesToast("Sepete Eklendi ")
                        viewModel.addBasketFavorite(product, requireContext())
                    }
                    else -> {
                        val bundle = Bundle()
                        bundle.putSerializable("prod", product.id)
                        Navigation.findNavController(binding.root)
                            .navigate(R.id.action_favoriFragment_to_productDetailsFragment,
                                bundle)
                    }
                }
            }
            binding.rcvFavorites.adapter = adapter
            binding.rcvFavorites.layoutManager = GridLayoutManager(requireContext(), 2)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}