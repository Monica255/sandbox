package com.example.network.data.pagination.repo

import com.example.network.data.pagination.model.BreedResponse

interface PagingRepository {
    suspend fun getBreed(page: Int, limit: Int): BreedResponse
}