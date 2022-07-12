package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.adapter.NotaAdapter
import com.example.myapplication.databinding.FragmentNotaBinding
import com.example.myapplication.viewModel.NotaViewModel

class NotaFragment : Fragment() {

    private lateinit var binding: FragmentNotaBinding

    val viewModel : NotaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding= FragmentNotaBinding.inflate(layoutInflater,container,false)
        // MOSTRAR NOTA
        viewModel.getNota().observe(viewLifecycleOwner) { notaList ->

            binding.rcvAllNotas.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvAllNotas.adapter = NotaAdapter(requireContext(), notaList)

        }

        binding.btnAddNota.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_navigation_home_to_navigation_escribirNota)
        }



        return binding.root
    }

}