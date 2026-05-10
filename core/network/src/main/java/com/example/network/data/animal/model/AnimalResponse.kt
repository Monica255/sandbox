package com.example.network.data.animal.model

import com.google.gson.annotations.SerializedName


data class FactResponse(
    @SerializedName("data")
    val data: List<AnimalResponse>
)

data class AnimalResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("attributes")
    val attributeResponse: AttributeResponse
)

data class AttributeResponse(
    @SerializedName("body")
    val body: String? = null
)
