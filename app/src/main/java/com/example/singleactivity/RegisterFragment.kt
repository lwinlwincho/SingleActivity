package com.example.singleactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.singleactivity.databinding.FragmentRegisterBinding
import com.example.singleactivity.databinding.FragmentSplashBinding

class RegisterFragment : Fragment() {

    private var _binding : FragmentRegisterBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnMain.setOnClickListener{
            val action =  RegisterFragmentDirections.actionRegisterFragmentToMainFragment()
            findNavController().navigate(action)
        }

        binding.btnBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }
}