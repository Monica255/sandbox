package com.example.sanbox.modules.pagination.utils

import com.example.network.data.pagination.model.CatBreedResponse
import com.example.sanbox.modules.pagination.model.Breed

object PagingMapper {
    fun CatBreedResponse.mapToDomain(): Breed = Breed(
        this.breed ?: "", this.country ?: ""
    )
}