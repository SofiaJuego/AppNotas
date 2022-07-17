package com.mynotas.notasApp.repository

import androidx.lifecycle.LiveData
import com.mynotas.notasApp.dao.NotaDao
import com.mynotas.notasApp.entity.NotaEntity

class NotaRepository(private val NotaDao: NotaDao) {

    fun allNotes(): LiveData<List<NotaEntity>> = NotaDao.getAll()

    fun getRojoNotes(): LiveData<List<NotaEntity>> = NotaDao.getRojoNotes()

    fun getVerdeNotes(): LiveData<List<NotaEntity>> = NotaDao.getVerdeNotes()

    fun getLilaNotes(): LiveData<List<NotaEntity>> = NotaDao.getLilaNotes()

    fun insert(notaEntity: NotaEntity) {
        NotaDao.insert(notaEntity)
    }

    fun delete(id: Int) {
        NotaDao.delete(id)
    }

    fun update(notaEntity: NotaEntity) {
        NotaDao.update(notaEntity)
    }
}