package com.example.sanbox.modules.patterns.facade

import javax.inject.Inject

class SayName @Inject constructor() {
    fun say(name: String) = "My name is $name"
}