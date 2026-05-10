package com.example.sanbox.modules.patterns.presenter

import androidx.lifecycle.ViewModel
import com.example.sanbox.modules.patterns.facade.Greeting
import com.example.sanbox.modules.patterns.strategy.CardPayment
import com.example.sanbox.modules.patterns.strategy.GooglePayPayment
import com.example.sanbox.modules.patterns.strategy.PaymentContext
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PatternsViewModel@Inject constructor(
    private val greeting: Greeting
): ViewModel() {
    val paymentContext = PaymentContext(CardPayment())
    fun sayGreetings(name: String) = greeting.sayGreeting(name)

    fun pay(amount: Double) = paymentContext.pay(amount)

    fun changeToCard() {
        paymentContext.setStrategy(CardPayment())
    }

    fun changeToEWallet() {
        paymentContext.setStrategy(GooglePayPayment())
    }
}