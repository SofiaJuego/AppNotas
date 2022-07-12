package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.dao.NotaDao
import com.example.myapplication.entity.NotaEntity

class NotaRepository (private val NotaDao: NotaDao){
    val allNotes: LiveData<List<NotaEntity>> = NotaDao.getAll()

    fun insert (notaEntity: NotaEntity){
        NotaDao.insert(notaEntity)
    }
    fun delete (id:Int){
        NotaDao.delete(id)
    }
    fun update (notaEntity: NotaEntity){
        NotaDao.update(notaEntity)
    }
}