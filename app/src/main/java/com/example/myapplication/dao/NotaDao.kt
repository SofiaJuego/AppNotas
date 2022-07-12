package com.example.myapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.entity.NotaEntity

@Dao
interface NotaDao {

    @Query("SELECT * FROM NotaEntity")
    fun getAll():LiveData<List<NotaEntity>>

    @Update
    fun update(notaEntity: NotaEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (notaEntity: NotaEntity)

    @Query("DELETE FROM NotaEntity WHERE id=:id")
    fun delete (id:Int)

}