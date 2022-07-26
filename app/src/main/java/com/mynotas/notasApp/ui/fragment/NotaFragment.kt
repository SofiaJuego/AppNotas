package com.mynotas.notasApp.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mynotas.notasApp.R
import com.mynotas.notasApp.adapter.NotaAdapter
import com.mynotas.notasApp.databinding.FragmentNotaBinding
import com.mynotas.notasApp.entity.NotaEntity
import com.mynotas.notasApp.viewModel.NotaViewModel

class NotaFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentNotaBinding

    private val viewModel: NotaViewModel by viewModels()
    private var oldMyNotas = arrayListOf<NotaEntity>()
    private lateinit var adapter: NotaAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNotaBinding.inflate(layoutInflater, container, false)

        //Menu dialog buscar
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        //Layout Manager
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rcvAllNotas.layoutManager = staggeredGridLayoutManager
        // <!-- MOSTRAR TODAS LAS NOTAS -->
        viewModel.getNota().observe(viewLifecycleOwner) { notaList ->
            oldMyNotas = notaList as ArrayList<NotaEntity>
            adapter = NotaAdapter(requireContext(), notaList)
            binding.rcvAllNotas.adapter = adapter


        }


        //<!-- FILTER POR COLOR -->
        with(binding){
            allNotas.setOnClickListener { viewModel.getNota() }
            filterRojo.setOnClickListener { viewModel.getRojoNotes() }
            filterVerde.setOnClickListener { viewModel.getVerdeNotes() }
            filterLila.setOnClickListener { viewModel.getLilaNotes() }
        }

        //Boton agregar nota
        binding.btnAddNota.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_navigation_home_to_navigation_escribirNota)
        }

        return binding.root
    }


    //Buscar nota

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.search_menu)
        item?.expandActionView()
        val buscarView = item.actionView as SearchView
        buscarView.queryHint = "Buscar nota.."
        buscarView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                notesFilter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })


    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

    //Buscar nota
    private fun notesFilter(query: String?) {

        val newFilteredList = arrayListOf<NotaEntity>()

        for (i in oldMyNotas) {
            if (i.Titulo.contains(query!!) || i.Subtitulo.contains(query)) {
                newFilteredList.add(i)
            }
        }

        adapter.filtering(newFilteredList)

    }
}


