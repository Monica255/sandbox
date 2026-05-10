package com.example.sanbox.modules.patterns.facade

class Greeting(
    val sayHai: SayHai,
    val sayName: SayName,
    val sayThankyou: SayThankyou
) {
    fun sayGreeting(name: String): String {
        return """
            ${sayHai.say()}
            ${sayName.say(name)}
            ${sayThankyou.say()}
        """.trimIndent()
    }
}