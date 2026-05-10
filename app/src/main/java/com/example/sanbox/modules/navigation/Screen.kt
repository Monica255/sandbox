package com.example.sanbox.modules.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}

enum class Modules(val moduleName: String, val id: Int) {
    GRPC("GRPC", 1),
    COR("Chain of Responsibility", 2),
    OTHERS("Others", 3),
    FETCH_DATA("Fetch Data", 4),
    ECDSA("Ecdsa", 5);

    companion object {
        fun fromId(id: Int): Modules? {
            return entries.find { it.id == id }
        }
    }
}