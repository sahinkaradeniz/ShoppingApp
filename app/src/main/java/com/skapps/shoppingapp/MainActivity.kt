package com.skapps.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

import androidx.navigation.ui.setupWithNavController
import com.skapps.shoppingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.navHostFragment
        ) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController)
        val view = binding.root
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.bottomNavigationView.visibility =
                if (destination.id == R.id.productDetailsFragment || destination.id == R.id.searchFragment || destination.id == R.id.splashFragment
                    || destination.id == R.id.historyPurchaseFragment|| destination.id==R.id.purchaseFragment || destination.id==R.id.commentProductFragment|| destination.id==R.id.commentDetailsFragment) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
        }
        setContentView(view)
    }
}