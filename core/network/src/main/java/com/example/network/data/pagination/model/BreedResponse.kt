package com.example.network.data.pagination.model

import com.google.gson.annotations.SerializedName

class BreedResponse(
    @SerializedName("current_page")
    val currentPage: Int? = null,
    @SerializedName("last_page")
    val lastPage: Int? = null,
    @SerializedName("data")
    val data: List<CatBreedResponse>? = null
)

data class CatBreedResponse(
    @SerializedName("breed")
    val breed: String?= null,
    @SerializedName("country")
    val country: String? = null
)