/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.prueba.data

import kotlinx.coroutines.flow.Flow

class OfflineVehiculoRepository(private val vehiculoDao: VehiculoDao) : VehiculoRepository {
    override fun getAllVehiculosStream(): Flow<List<Vehiculo>> = vehiculoDao.getAllVehiculos()

    override fun getVehiculoStream(id: Int): Flow<Vehiculo?> = vehiculoDao.getVehiculo(id)

    override suspend fun insertVehiculo(vehiculo: Vehiculo) = vehiculoDao.insert(vehiculo)

    override suspend fun deleteVehiculo(vehiculo: Vehiculo) = vehiculoDao.delete(vehiculo)

    override suspend fun updateVehiculo(vehiculo: Vehiculo) = vehiculoDao.update(vehiculo)
}