package com.example.sanbox.modules.patterns.cor.handler

interface Handler {

    fun canHandle(data: String): Boolean

    fun handle(data: Int): String
}