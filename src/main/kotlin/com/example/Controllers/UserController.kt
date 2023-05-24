package com.example.Controllers

import DBManagers.UserManager
import com.example.Entities.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.usersRouting() {
    var userManager = UserManager()
    var user1=User("vlan", "4567")
    user1.setMale("male")
    user1.setHeight(180.0F)
    user1.setWeight(58.0F)
    userManager.addUser(user1)
//    user1.setMale("female")
//    user1.setBirthDate(2004, 6, 27)
//    var user2=User("spuna", "458745")
//    var user3=User("sd", "556556")
//    userManager.addUser(user1)
//    userManager.addUser(user2)
//    userManager.addUser(user3)
//    userManager.setUserData(user1)
//    userManager.setUserData(user2)
//    userManager.setUserData(user3)
    route("api/users") {
        get("/get/{user_id}") {
            val userData = userManager.getUserData(call.parameters["user_id"].toString())
            if(userData!=null) call.respond(HttpStatusCode.OK, userData.toJson())
            else call.respond(HttpStatusCode.BadRequest, "Not found ${call.parameters["user_id"]}")
        }
        post("/register") {
            val userRequest = call.receive<User>()

           if (userManager.addUser(userRequest)) call.respond(HttpStatusCode.OK, "Success")
               else call.respond(HttpStatusCode.BadRequest, "Username exists")
        }

        post("/login") {
            val userRequest = call.receive<User>()

            if (userManager.userExists(userRequest)) {
                call.respond(HttpStatusCode.OK, "Login successful")
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Invalid username or password")
            }
        }
        post("/setData/{user_id}"){
            val userRequest = call.receive<User>()
            if (userManager.setUserData(call.parameters["user_id"].toString(), userRequest))call.respond(HttpStatusCode.OK, "Updated")
            else call.respond(HttpStatusCode.BadRequest, "Not found")
        }
    }
}

fun Application.registerRoutes() {
    routing {
        usersRouting()
    }
}


