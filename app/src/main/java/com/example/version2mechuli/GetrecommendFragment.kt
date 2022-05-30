package com.example.version2mechuli

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.version2mechuli.databinding.FragmentGetrecommendBinding

class GetrecommendFragment : Fragment() {

    lateinit var binding : FragmentGetrecommendBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetrecommendBinding.inflate(inflater, container, false)
        return binding.root
    }

}