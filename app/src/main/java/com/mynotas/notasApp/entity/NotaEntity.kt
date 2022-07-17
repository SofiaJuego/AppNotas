package com.mynotas.notasApp.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "NotaEntity")
@Parcelize
class NotaEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var Titulo: String,
    var Subtitulo: String,
    var Contenido: String,
    var Date: String,
    var priority: String,
) : Parcelable








