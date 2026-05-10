package com.example.network.data.animal.service

import com.example.network.data.animal.model.FactResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimalFactService {
    @GET("/api/v2/facts")
    suspend fun getAnimal(
        @Query("limit") amount: Int
    ): FactResponse
}