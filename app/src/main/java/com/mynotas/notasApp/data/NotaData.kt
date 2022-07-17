package com.mynotas.notasApp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mynotas.notasApp.dao.NotaDao
import com.mynotas.notasApp.entity.NotaEntity

@Database(
    entities = [NotaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NotaData : RoomDatabase() {
    abstract fun notaDao(): NotaDao

    companion object {

        @Volatile
        private var INSTANCE: NotaData? = null

        fun getDatabase(context: Context): NotaData {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotaData::class.java,
                    "NotaEntity"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }

}