package com.example.Controllers

import User
import DBManagers.UserManager
import io.ktor.server.routing.*
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.respond
import io.ktor.server.request.receive


fun Route.usersRouting() {

    var userManager = UserManager()
    userManager.addUser("vlan", "4567")
    userManager.addUser("spuna", "458745")
    userManager.addUser("sd", "556556")

    route("api/users") {
        get("/get") {
            call.respond(HttpStatusCode.OK, userManager.usersList())
        }
        post("/register") {
            val userRequest = call.receive<User>()
            val username = userRequest.username
            val password = userRequest.pass

            if (userManager.usernameExists(username, password)) {
                call.respond(HttpStatusCode.BadRequest, "Username already exists")
            } else {
                userManager.addUser(username, password)
                call.respond(HttpStatusCode.OK, "Registration successful")
            }
        }

        post("/login") {
            val userRequest = call.receive<User>()
            val username = userRequest.username
            val password = userRequest.pass

            if (userManager.userExists(username, password)) {
                call.respond(HttpStatusCode.OK, "Login successful")
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Invalid username or password")
            }
        }
    }
}

fun Application.registerRoutes() {
    routing {
        usersRouting()
    }
}


