package com.example.singleactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.singleactivity.databinding.FragmentMainBinding
import kotlin.concurrent.fixedRateTimer

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val navController by lazy {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() {
        binding.bottomNavView.setupWithNavController(navController)
        binding.bottomNavView.setOnItemReselectedListener { item ->
            if (binding.bottomNavView.selectedItemId == item.itemId) {
                return@setOnItemReselectedListener
            }
        }

/*
        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_main).navigate(R.id.homeFragmentMain)
                    true
                }
                R.id.live -> {
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_main).navigate(R.id.liveFragmentMain)
                    true
                }
                R.id.profile -> {
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_main).navigate(R.id.profileFragmentMain)
                    true
                }
                else -> {
                    false
                }
            }
        }
        binding.bottomNavView.selectedItemId = R.id.homeFragmentMain
*/
    }
}