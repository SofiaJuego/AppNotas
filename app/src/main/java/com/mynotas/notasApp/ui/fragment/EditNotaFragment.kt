package com.mynotas.notasApp.ui.fragment

import android.os.Bundle
import android.text.format.DateFormat
import android.text.format.DateFormat.format
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
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

    val notes by navArgs<EditNotaFragmentArgs>()
    var priority: String = "1"
    val viewModel: NotaViewModel by viewModels()


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

            ubdateNotas(it)
        }




        return binding.root
    }

    //EDITAR NOTA
    private fun ubdateNotas(it: View?) {

        val titulo = binding.EdittituloNota.text.toString()
        val subtitulo = binding.EditsubtituloNota.text.toString()
        val contenido = binding.EditcontenidoNota.text.toString()
        val d = Date()
        val dateNota: CharSequence = DateFormat.format("MMMM d, yyy", d.time)

        val notaEntity = NotaEntity(
            notes.data.id,
            Titulo = titulo,
            Subtitulo = subtitulo,
            Contenido = contenido,
            Date = dateNota.toString(),
            priority)
        viewModel.updateNota(notaEntity)
        Toast.makeText(requireContext(), "Se edito la nota correctamente", Toast.LENGTH_LONG).show()

        Navigation.findNavController(it!!)
            .navigate(R.id.action_navigation_editarNota_to_navigation_home)

    }

    //MENU DE ELIMINAR
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)


    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

        if (menuItem.itemId == R.id.eliminar_menu) {

            deleteNota()

        }
        return true

    }

    private fun deleteNota() {

        val dialogDelete = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
        dialogDelete.setContentView(R.layout.dialog_delete)

        val texViewEliminar = dialogDelete.findViewById<TextView>(R.id.dialog_eliminar)
        val texViewCancelar = dialogDelete.findViewById<TextView>(R.id.dialog_cancelar)

        texViewEliminar?.setOnClickListener {
            viewModel.deleteNota(notes.data.id!!)
            Toast.makeText(requireContext(), "Se elimino la nota correctamente", Toast.LENGTH_LONG)
                .show()
            findNavController().navigate(R.id.action_navigation_editarNota_to_navigation_home)

            dialogDelete.dismiss()
        }


        texViewCancelar?.setOnClickListener {
            dialogDelete.dismiss()
        }


        dialogDelete.show()

    }
}






