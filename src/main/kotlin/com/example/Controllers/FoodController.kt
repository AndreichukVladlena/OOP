package com.example.Controllers

import com.example.Entities.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import src.DBManagers.ActualFoodManager


fun Route.actualFoodRouting() {
    var actualFoodManager = ActualFoodManager()

    route("api/foodManager/{user_id}") {
        get("/get/{user_id}") {
            val food = actualFoodManager.getActualFoodItemData(call.parameters["user_id"].toString())
            if(userData!=null) call.respond(HttpStatusCode.OK, userData.toJson())
            else call.respond(HttpStatusCode.BadRequest, "Not found ${call.parameters["user_id"]}")
        }
        post("/register") {
            val userRequest = call.receive<User>()

            if (actualFoodManager.addUser(userRequest)) call.respond(HttpStatusCode.OK, "Success")
            else call.respond(HttpStatusCode.BadRequest, "Username exists")
        }

        post("/login") {
            val userRequest = call.receive<User>()

            if (actualFoodManager.userExists(userRequest)) {
                call.respond(HttpStatusCode.OK, "Login successful")
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Invalid username or password")
            }
        }
        post("/setData/{user_id}"){
            val userRequest = call.receive<User>()
            if (actualFoodManager.setUserData(call.parameters["user_id"].toString(), userRequest))call.respond(HttpStatusCode.OK, "Updated")
            else call.respond(HttpStatusCode.BadRequest, "Not found")
        }
        delete("/delete/{user_id}") {
            if (actualFoodManager.removeUser(call.parameters["user_id"].toString()))call.respond(HttpStatusCode.OK, "Deleted")
            else call.respond(HttpStatusCode.BadRequest, "Not found")
        }
    }
}

