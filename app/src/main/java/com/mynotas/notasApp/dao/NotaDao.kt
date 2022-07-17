package com.mynotas.notasApp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mynotas.notasApp.entity.NotaEntity

@Dao
interface NotaDao {

    @Query("SELECT * FROM NotaEntity")
    fun getAll(): LiveData<List<NotaEntity>>

    @Query("SELECT * FROM NotaEntity WHERE priority=3")
    fun getLilaNotes(): LiveData<List<NotaEntity>>

    @Query("SELECT * FROM NotaEntity WHERE priority= 2")
    fun getRojoNotes(): LiveData<List<NotaEntity>>

    @Query("SELECT * FROM NotaEntity WHERE priority=1")
    fun getVerdeNotes(): LiveData<List<NotaEntity>>


    @Update
    fun update(notaEntity: NotaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notaEntity: NotaEntity)

    @Query("DELETE FROM NotaEntity WHERE id=:id")
    fun delete(id:Int)

}