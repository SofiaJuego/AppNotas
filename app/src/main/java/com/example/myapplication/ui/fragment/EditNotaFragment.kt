package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentEditNotaBinding


class EditNotaFragment : Fragment() {
    private lateinit var binding: FragmentEditNotaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentEditNotaBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}
