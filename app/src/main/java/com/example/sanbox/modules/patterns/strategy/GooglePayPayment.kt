package com.example.sanbox.modules.patterns.strategy

class GooglePayPayment: PaymentStrategy {
    override fun pay(amount: Double): String {
        return "Payment $amount with GooglePay"
    }
}