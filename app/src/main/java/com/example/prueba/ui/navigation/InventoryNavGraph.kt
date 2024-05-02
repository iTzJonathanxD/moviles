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

package com.example.prueba.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.inventory.ui.item.VehiculoDetailsDestination
import com.example.inventory.ui.item.VehiculoDetailsScreen
import com.example.inventory.ui.item.VehiculoEditDestination
import com.example.inventory.ui.item.VehiculoEditScreen
import com.example.inventory.ui.item.VehiculoEntryDestination
import com.example.inventory.ui.item.VehiculoEntryScreen
import com.example.prueba.ui.home.HomeDestination
import com.example.prueba.ui.home.HomeScreen

/**
 * Provides Navigation graph for the application.
 */

@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(VehiculoEntryDestination.route) },
                navigateToItemUpdate = {
                    navController.navigate("${VehiculoDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = VehiculoEntryDestination.route) {
            VehiculoEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = VehiculoDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(VehiculoDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            VehiculoDetailsScreen(
                navigateToEditItem = { navController.navigate("${VehiculoEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() }
            )
        }
        composable(
            route = VehiculoEditDestination.routeWithArgs,
            arguments = listOf(navArgument(VehiculoEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            VehiculoEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}