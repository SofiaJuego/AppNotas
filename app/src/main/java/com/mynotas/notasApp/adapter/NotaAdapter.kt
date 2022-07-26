package com.mynotas.notasApp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mynotas.notasApp.R
import com.mynotas.notasApp.databinding.FragmentItemBinding
import com.mynotas.notasApp.entity.NotaEntity
import com.mynotas.notasApp.ui.fragment.NotaFragmentDirections


class NotaAdapter(val requireContext: Context, var notaList: List<NotaEntity>) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

    fun filtering(newFilteredList: ArrayList<NotaEntity>) {
        notaList = newFilteredList
        notifyDataSetChanged()
    }


    class NotaViewHolder(val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {

        return NotaViewHolder(FragmentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val data = notaList[position]
        holder.binding.textViewTitulo.text = data.Titulo
        holder.binding.textViewSub.text = data.Subtitulo
        holder.binding.textViewContenido.text = data.Contenido


        when (data.priority) {
            "1" -> {
                holder.binding.ViewColor.setBackgroundResource(R.drawable.green_box)
            }
            "2" -> {
                holder.binding.ViewColor.setBackgroundResource(R.drawable.pink_box)
            }
            "3" -> {
                holder.binding.ViewColor.setBackgroundResource(R.drawable.purple_box)
            }

        }
        holder.binding.root.setOnClickListener {

            val action = NotaFragmentDirections.actionNavigationHomeToNavigationEditarNota(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = notaList.size

}