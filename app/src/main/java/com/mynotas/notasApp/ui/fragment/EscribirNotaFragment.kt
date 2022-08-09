package com.mynotas.notasApp.ui.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mynotas.notasApp.R
import com.mynotas.notasApp.databinding.FragmentEscribirNotaBinding
import com.mynotas.notasApp.entity.NotaEntity
import com.mynotas.notasApp.viewModel.NotaViewModel
import java.util.*


class EscribirNotaFragment : Fragment() {
    private lateinit var binding: FragmentEscribirNotaBinding
    private var priority: String = "1"
    private val viewModel: NotaViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEscribirNotaBinding.inflate(layoutInflater, container, false)

        binding.btnSaveNota.setOnClickListener {
            validateNote()
        }
        //AGREGAR COLOR A LA NOTA
        binding.Cgreen.setOnClickListener {
            priority = "1"
            binding.Cgreen.setImageResource(R.drawable.ic_baseline_check_24)
            binding.Cpink.setImageResource(0)
            binding.Cpurple.setImageResource(0)
        }
        binding.Cpink.setOnClickListener {
            priority = "2"
            binding.Cpink.setImageResource(R.drawable.ic_baseline_check_24)
            binding.Cgreen.setImageResource(0)
            binding.Cpurple.setImageResource(0)
        }
        binding.Cpurple.setOnClickListener {
            priority = "3"
            binding.Cpurple.setImageResource(R.drawable.ic_baseline_check_24)
            binding.Cpink.setImageResource(0)
            binding.Cgreen.setImageResource(0)
        }
        return binding.root
    }

    // CREAMOS LA NOTA
    private fun validateNote() {
        val titulo = binding.tituloNota.text.toString()
        val subtitulo = binding.subtituloNota.text.toString()
        val contenido = binding.contenidoNota.text.toString()

        if (titulo.isEmpty() || contenido.isEmpty()) {
            Toast.makeText(requireContext(), "Llena los campos", Toast.LENGTH_LONG).show()
        } else {
            createNote(titulo = titulo, subtitulo = subtitulo, contenido = contenido)
            Toast.makeText(requireContext(), "Se creo la nota correctamente", Toast.LENGTH_LONG).show()
            activity?.onBackPressed()
        }
    }

    private fun createNote(
        titulo: String,
        subtitulo: String,
        contenido: String,
    ) {
        val d = Date()
        val dateNota: CharSequence = DateFormat.format("MMMM d, yyy", d.time)

        val notaEntity = NotaEntity(
            null,
            Titulo = titulo,
            Subtitulo = subtitulo,
            Contenido = contenido,
            Date = dateNota.toString(),
            priority
        )

        viewModel.addNota(notaEntity)
    }

}