package com.example.network.data.pagination.service

import com.example.network.data.pagination.model.BreedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PaginationService {
    @GET("/breeds")
    suspend fun getBreed(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): BreedResponse
}