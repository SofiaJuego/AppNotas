package com.example.myapplication.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "NotaEntity")
class NotaEntity (
   @PrimaryKey(autoGenerate = true)
   var id: Int? = null,
   var Titulo: String,
   var Subtitulo: String,
   var Contenido: String,
   var Date: String,
   var priority: String
)







