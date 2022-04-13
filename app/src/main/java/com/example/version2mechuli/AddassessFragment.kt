package com.example.version2mechuli

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.version2mechuli.databinding.FragmentAddassessBinding

class AddassessFragment : Fragment() {

    lateinit var binding : FragmentAddassessBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddassessBinding.inflate(inflater, container, false)


        return binding.root
    }

}