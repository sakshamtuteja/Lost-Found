package com.example.lostfound

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lostfound.databinding.FragmentFoundBinding
import com.example.lostfound.databinding.FragmentLostBinding

class Found : Fragment() {
    private var _binding: FragmentFoundBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFoundBinding.inflate(inflater, container, false)
        binding.floatingActionButton2.setOnClickListener {
            activity?.let {
                val intent = Intent(it, Uploadfound::class.java)
                it.startActivity(intent)
            }
        }
        return binding.root
    }

}
