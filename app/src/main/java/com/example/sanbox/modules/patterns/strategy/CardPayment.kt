package com.example.sanbox.modules.patterns.strategy

class CardPayment: PaymentStrategy {
    override fun pay(amount: Double): String {
        return "Payment $amount with card"
    }
}