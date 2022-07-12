package com.example.myapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.db.dao.data.NotaData
import com.example.myapplication.entity.NotaEntity
import com.example.myapplication.repository.NotaRepository

class NotaViewModel(application: Application) : AndroidViewModel(application) {

    val repository : NotaRepository

    init {
        val dao = NotaData.getDatabase(application).notaDao()
        repository = NotaRepository(dao)
    }

    fun addNota(notaEntity: NotaEntity){
        repository.insert(notaEntity)
    }
    fun getNota(): LiveData<List<NotaEntity>> = repository.allNotes

    fun deleteNota(id:Int) {
        repository.delete(id)
    }
    fun updateNota(notaEntity: NotaEntity){
        repository.update(notaEntity)

    }
}