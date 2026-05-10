package com.example.network.data.animal.repository

import com.example.network.data.animal.model.FactResponse

interface AnimalRepository {

    suspend fun getAnimal(): FactResponse
}