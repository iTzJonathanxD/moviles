package com.example.prueba.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [Vehiculo::class], version = 1, exportSchema = false)
abstract class VehiculoDatabase : RoomDatabase() {

    abstract fun VehiculoDao(): VehiculoDao

    companion object {
        @Volatile
        private var Instance: VehiculoDatabase? = null

        fun getDatabase(context: Context): VehiculoDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, VehiculoDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}