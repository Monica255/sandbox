package com.example.sanbox.modules.others.data

import kotlinx.coroutines.delay

class OthersRepository {
    suspend fun getProducts(q: String): List<String> {
        delay(2000)
        return listOf(
            "$q Product A",
            "$q Product B",
            "$q Product C",
            "$q Product D",
        )
    }
}