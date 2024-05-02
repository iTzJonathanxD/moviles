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

package com.example.prueba.ui.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.prueba.data.Vehiculo
import com.example.prueba.data.VehiculoRepository
import java.text.NumberFormat

/**
 * ViewModel to validate and insert items in the Room database.
 */
class VehiculoEntryViewModel(private val vehiculosRepository: VehiculoRepository) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Updates the [itemUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(vehiculoDetails: VehiculoDetails) {
        itemUiState =
            ItemUiState(vehiculoDetails = vehiculoDetails, isEntryValid = validateInput(vehiculoDetails))
    }

    private fun validateInput(uiState: VehiculoDetails = itemUiState.vehiculoDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && modelo.isNotBlank() && color.isNotBlank() && precio.isNotBlank()
        }
    }
    suspend fun saveItem() {
        if (validateInput()) {
            vehiculosRepository.insertVehiculo(itemUiState.vehiculoDetails.toItem())
        }
    }
}

/**
 * Represents Ui State for an Item.
 */
data class ItemUiState(
    val vehiculoDetails: VehiculoDetails = VehiculoDetails(),
    val isEntryValid: Boolean = false
)

data class VehiculoDetails(
    val id: Int = 0,
    val name: String = "",
    val modelo: String = "",
    val color: String = "",
    val precio: String = "",
)


fun VehiculoDetails.toItem(): Vehiculo = Vehiculo(
    id = id,
    name = name,
    modelo = modelo,
    color = color,
    precio = precio.toDoubleOrNull() ?: 0.0
)

fun Vehiculo.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(precio)
}

/**
 * Extension function to convert [Item] to [ItemUiState]
 */
fun Vehiculo.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    vehiculoDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Item] to [ItemDetails]
 */
fun Vehiculo.toItemDetails(): VehiculoDetails = VehiculoDetails(
    id = id,
    name = name,
    modelo = modelo,
    color = color,
    precio = precio.toString(),
)

