package com.example.sanbox.modules.cor.handler

interface Handler {

    fun canHandle(data: String): Boolean

    fun handle(data: Int): String
}