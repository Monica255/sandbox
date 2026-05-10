package com.example.network.data.pagination.repo

import com.example.network.data.pagination.model.BreedResponse
import com.example.network.data.pagination.service.PaginationService
import javax.inject.Inject

class PagingRepositoryImpl @Inject constructor(
    private val apiService: PaginationService
): PagingRepository {
    override suspend fun getBreed(page: Int, limit: Int): BreedResponse {
        return apiService.getBreed(page = page, limit = limit)
    }
}