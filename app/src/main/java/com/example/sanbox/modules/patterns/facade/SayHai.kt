package com.example.sanbox.modules.patterns.facade

import javax.inject.Inject

class SayHai@Inject constructor() {
    fun say(): String = "Hai"
}