package com.example.sanbox.modules.patterns.cor.handler

class HandlerOdd : Handler {
    override fun canHandle(data: String): Boolean = data.toIntOrNull()?.rem(2) == 1

    override fun handle(data: Int): String {
        return "Data $data is handled by odd handler"
    }
}