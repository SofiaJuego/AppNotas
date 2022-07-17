package com.mynotas.notasApp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mynotas.notasApp.data.NotaData
import com.mynotas.notasApp.entity.NotaEntity
import com.mynotas.notasApp.repository.NotaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotaViewModel(application: Application) : AndroidViewModel(application) {

    val repository: NotaRepository

    init {
        val dao = NotaData.getDatabase(application).notaDao()
        repository = NotaRepository(dao)
    }

    fun addNota(notaEntity: NotaEntity) {
        repository.insert(notaEntity)
    }

    fun getNota(): LiveData<List<NotaEntity>> = repository.allNotes()

    fun getRojoNotes(): LiveData<List<NotaEntity>> = repository.getRojoNotes()

    fun getVerdeNotes(): LiveData<List<NotaEntity>> = repository.getVerdeNotes()

    fun getLilaNotes(): LiveData<List<NotaEntity>> = repository.getLilaNotes()


    fun deleteNota(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(id)
        }
    }

    fun updateNota(notaEntity: NotaEntity) {
        repository.update(notaEntity)

    }
}