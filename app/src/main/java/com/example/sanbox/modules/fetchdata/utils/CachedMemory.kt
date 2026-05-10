package com.example.sanbox.modules.fetchdata.utils

class CachedMemory<T> {

    val mapped = mutableMapOf<String, T>()

    fun put(key: String, value: T){
        mapped[key] = value
    }

    fun get(key: String): T? {
        return mapped[key]
    }

    fun putAll(map : Map<String, T>){
        mapped.putAll(map)
    }
}