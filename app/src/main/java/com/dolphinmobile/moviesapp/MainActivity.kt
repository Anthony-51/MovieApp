package com.dolphinmobile.moviesapp

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.dolphinmobile.moviesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

      private lateinit var binding: ActivityMainBinding

      override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val navView: BottomNavigationView = binding.navView

            val navController = findNavController(R.id.nav_host_fragment_activity_main)
            navView.setupWithNavController(navController)

            navController.addOnDestinationChangedListener{ _, destination, _ ->
                  if (destination.id == R.id.detailFragment){
                        navView.visibility = View.GONE
                  }else{
                        navView.visibility = View.VISIBLE
                  }
            }
      }
}