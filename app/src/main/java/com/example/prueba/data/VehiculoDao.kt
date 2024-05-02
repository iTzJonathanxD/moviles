package com.example.prueba.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface VehiculoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vehiculo: Vehiculo)

    @Update
    suspend fun update(vehiculo: Vehiculo)

    @Delete
    suspend fun delete(vehiculo: Vehiculo)

    @Query("SELECT * from vehiculo WHERE id = :id")
    fun getVehiculo(id: Int): Flow<Vehiculo>

    @Query("SELECT * from vehiculo ORDER BY name ASC")
    fun getAllVehiculos(): Flow<List<Vehiculo>>
}