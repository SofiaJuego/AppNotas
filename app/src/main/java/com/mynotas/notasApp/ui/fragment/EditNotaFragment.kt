package com.mynotas.notasApp.ui.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mynotas.notasApp.R
import com.mynotas.notasApp.databinding.FragmentEditNotaBinding
import com.mynotas.notasApp.entity.NotaEntity
import com.mynotas.notasApp.viewModel.NotaViewModel
import java.util.*


class EditNotaFragment : Fragment(), MenuProvider {
    private lateinit var binding: FragmentEditNotaBinding

    private val notes by navArgs<EditNotaFragmentArgs>()
    var priority: String = "1"
    private val viewModel: NotaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEditNotaBinding.inflate(layoutInflater, container, false)


        //Menu eliminar
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)


        //Editar
        binding.EdittituloNota.setText(notes.data.Titulo)
        binding.EditsubtituloNota.setText(notes.data.Subtitulo)
        binding.EditcontenidoNota.setText(notes.data.Contenido)

        when (notes.data.priority) {
            "1" -> {
                priority = "1"
                binding.Cgreen.setImageResource(R.drawable.ic_baseline_check_24)
                binding.Cpink.setImageResource(0)
                binding.Cpurple.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.Cpink.setImageResource(R.drawable.ic_baseline_check_24)
                binding.Cgreen.setImageResource(0)
                binding.Cpurple.setImageResource(0)
            }
            "3" -> {
                priority = "2"
                binding.Cpurple.setImageResource(R.drawable.ic_baseline_check_24)
                binding.Cpink.setImageResource(0)
                binding.Cgreen.setImageResource(0)
            }

        }

        //EDITAR COLOR A LA NOTA
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

        binding.btnSaveEditNota.setOnClickListener {

            validateEditNote()
        }




        return binding.root
    }
    //Validamos que los campos esten llenos
    private fun validateEditNote() {
        val titulo = binding.EdittituloNota.text.toString()
        val subtitulo = binding.EditsubtituloNota.text.toString()
        val contenido = binding.EditcontenidoNota.text.toString()

        if (titulo.isEmpty() || contenido.isEmpty()) {
            Toast.makeText(requireContext(), "Llena los campos", Toast.LENGTH_SHORT).show()
        } else {
            udateNotas(titulo = titulo, subtitulo = subtitulo, contenido = contenido)
            Toast.makeText(requireContext(), "Se edito la nota correctamente", Toast.LENGTH_SHORT).show()
            activity?.onBackPressed()
        }
    }

    //EDITAR NOTA
    private fun udateNotas(titulo: String, subtitulo: String, contenido: String) {
        val d = Date()
        val dateNota: CharSequence = DateFormat.format("MMMM d, yyy", d.time)

        val notaEntity = NotaEntity(
            notes.data.id,
            Titulo = titulo,
            Subtitulo = subtitulo,
            Contenido = contenido,
            Date = dateNota.toString(),
            priority
        )
        viewModel.updateNota(notaEntity)



    }

    //MENU DE ELIMINAR
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.eliminar_menu -> {
                openDialogDelete()
            }
            else -> return false

        }
        return true
    }

    private fun openDialogDelete() {

        val dialogDelete = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
        dialogDelete.setContentView(R.layout.dialog_delete)

        val texViewEliminar = dialogDelete.findViewById<TextView>(R.id.dialog_eliminar)
        val texViewCancelar = dialogDelete.findViewById<TextView>(R.id.dialog_cancelar)

        texViewEliminar?.setOnClickListener {
            notes.data.id?.let { idNota ->
                viewModel.deleteNota(idNota)
                Toast.makeText(requireContext(), "Se elimino la nota correctamente", Toast.LENGTH_LONG)
                    .show()
                findNavController().navigate(R.id.action_navigation_editarNota_to_navigation_home)
            }
            dialogDelete.dismiss()
        }


        texViewCancelar?.setOnClickListener {
            dialogDelete.dismiss()
        }


        dialogDelete.show()

    }
}






