package com.example.sanbox.modules.patterns.strategy

interface PaymentStrategy {
    fun pay(amount: Double): String
}