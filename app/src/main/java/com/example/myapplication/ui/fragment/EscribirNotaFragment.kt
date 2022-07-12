package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentEscribirNotaBinding
import com.example.myapplication.entity.NotaEntity
import com.example.myapplication.viewModel.NotaViewModel
import java.util.*

class EscribirNotaFragment : Fragment() {
    private lateinit var binding: FragmentEscribirNotaBinding
    var priority:String = "1"
    val viewModel : NotaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentEscribirNotaBinding.inflate(layoutInflater,container,false)

        binding.btnSaveNota.setOnClickListener{
            createNota(it)
        }
        //AGREGAR COLOR A LA NOTA
        binding.Cgreen.setOnClickListener {
            priority = "1"
            binding.Cgreen.setImageResource(R.drawable.ic_baseline_check_24)
            binding.Cpink.setImageResource(0)
            binding.Cyellow.setImageResource(0)
        }
        binding.Cpink.setOnClickListener {
            priority = "2"
            binding.Cpink.setImageResource(R.drawable.ic_baseline_check_24)
            binding.Cgreen.setImageResource(0)
            binding.Cyellow.setImageResource(0)
        }
        binding.Cyellow.setOnClickListener {
            priority = "3"
            binding.Cyellow.setImageResource(R.drawable.ic_baseline_check_24)
            binding.Cpink.setImageResource(0)
            binding.Cgreen.setImageResource(0)
        }


        return binding.root
    }
    // CREAMOS LA NOTA
    private fun createNota(it: View?) {
        val titulo = binding.tituloNota.text.toString()
        val subtitulo = binding.subtituloNota.text.toString()
        val contenido = binding.contenidoNota.text.toString()
        val d = Date()
        val dateNota: CharSequence= DateFormat.format("MMMM d, yyy", d.time)

        val notaEntity = NotaEntity(null,
            Titulo = titulo,
            Subtitulo = subtitulo,
            Contenido = contenido,
            Date = dateNota.toString(),
            priority )
        viewModel.addNota(notaEntity)
        Toast.makeText(requireContext(), "Se creo la nota correctamente", Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!).navigate(R.id.action_navigation_escribirNota_to_navigation_home)

    }

}