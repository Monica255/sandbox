package com.example.network.data.animal.repository

import com.example.network.data.animal.model.FactResponse
import com.example.network.data.animal.service.AnimalFactService
import javax.inject.Inject

class AnimalRepositoryImpl @Inject constructor(
    private val apiService: AnimalFactService
): AnimalRepository {
    override suspend fun getAnimal(): FactResponse {
        return apiService.getAnimal(5)
    }
}