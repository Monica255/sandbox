package com.example.sanbox.modules.cor

import com.example.sanbox.modules.cor.handler.Handler
import com.example.sanbox.modules.cor.handler.HandlerEven
import com.example.sanbox.modules.cor.handler.HandlerOdd

class Manager {

    val handlers: List<Handler> = listOf(
        HandlerOdd(),
        HandlerEven()
    )

    fun process(data: String): String {
        val handler = handlers.firstOrNull { it.canHandle(data) }
        return handler?.handle(data.toInt()) ?: "No handler can handle"
    }
}