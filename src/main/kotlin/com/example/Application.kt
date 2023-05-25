package com.example

import com.example.Controllers.actualFoodRouting
import com.example.Controllers.registerRoutes
import com.example.Controllers.usersRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    registerRoutes()
}
fun Application.registerRoutes() {
    routing {
        usersRouting()
        actualFoodRouting()
    }
}
