package com.example.sanbox.modules.patterns.facade

import javax.inject.Inject

class SayThankyou @Inject constructor() {
    fun say(): String = "Thankyouu!"
}