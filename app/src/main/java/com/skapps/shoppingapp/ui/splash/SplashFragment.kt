package com.skapps.shoppingapp.ui.splash

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.utils.OrangechangeStatusBarColor



class SplashFragment : Fragment() {
    private lateinit var viewModel: SplashViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        requireActivity().OrangechangeStatusBarColor(true)
        viewModel.timer.observe(viewLifecycleOwner){
            if (it){
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }
    }


}