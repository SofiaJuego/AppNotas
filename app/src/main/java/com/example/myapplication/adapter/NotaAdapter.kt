package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentItemBinding
import com.example.myapplication.entity.NotaEntity


class NotaAdapter(val requireContext: Context, val notaList: List<NotaEntity>) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>(){

    class NotaViewHolder(val binding:FragmentItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {

        return NotaViewHolder(FragmentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val data = notaList[position]
        holder.binding.textViewTitulo.text = data.Titulo
        holder.binding.textViewContenido.text = data.Contenido
        holder.binding.textViewfecha.text = data.Date

        when (data.priority){
            "1" -> {
                holder.binding.ViewColor.setBackgroundResource(R.drawable.green_box)
            }
            "2" -> {
                holder.binding.ViewColor.setBackgroundResource(R.drawable.pink_box)
            }
            "3" -> {
                holder.binding.ViewColor.setBackgroundResource(R.drawable.yellow_box)
            }

        }
    }

    override fun getItemCount()= notaList.size

}