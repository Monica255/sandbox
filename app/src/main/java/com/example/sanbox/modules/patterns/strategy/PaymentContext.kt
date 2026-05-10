package com.example.sanbox.modules.patterns.strategy

class PaymentContext(
    private var strategy: PaymentStrategy,
) {
    fun setStrategy(strategy: PaymentStrategy) {
        this.strategy = strategy
    }

    fun pay(amount: Double): String {
        return strategy.pay(amount)
    }
}