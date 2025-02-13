package com.task.vodafone.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.task.vodafone.R
import com.task.vodafone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navHost: NavHostFragment by lazy { (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_complaint) as NavHostFragment) }
    val navController: NavController by lazy { navHost.navController }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}