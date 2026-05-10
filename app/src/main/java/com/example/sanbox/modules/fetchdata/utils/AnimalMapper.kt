package com.example.sanbox.modules.fetchdata.utils

import com.example.network.data.animal.model.AnimalResponse
import com.example.sanbox.modules.fetchdata.presenter.Animal

object AnimalMapper {
    fun AnimalResponse.mapToDomain(): Animal = Animal(
        this.id ?: "", this.attributeResponse.body ?: ""
    )
}