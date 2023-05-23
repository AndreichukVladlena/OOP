package com.example.Controllers

import User
import com.example.ErrorTracker.*
import com.example.const.*
import DBManagers.UserManager
import io.ktor.server.routing.*
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.respond
import io.ktor.server.request.receive


fun Route.usersRouting() {
    var userManager = UserManager()
    var user1=User("vlan", "4567")
    user1.setMale("female")
    var user2=User("spuna", "458745")
    var user3=User("sd", "556556")
    userManager.addUser(user1)
    userManager.addUser(user2)
    userManager.addUser(user3)

    route("api/users") {
        get("/get") {
            call.respond(HttpStatusCode.OK, userManager.usersList())
        }
        post("/register") {
            val userRequest = call.receive<User>()

            if (userManager.usernameExists(userRequest)) {
                call.respond(HttpStatusCode.BadRequest, "Username already exists")
            } else {
                userManager.addUser(userRequest)
                call.respond(HttpStatusCode.OK, "Registration successful")
            }
        }

        post("/login") {
            val userRequest = call.receive<User>()

            if (userManager.userExists(userRequest)) {
                call.respond(HttpStatusCode.OK, "Login successful")
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Invalid username or password")
            }
        }
        post("/setData"){
            val userRequest = call.receive<User>()
            val userMale=call.receive<String>()
            if (userRequest in userManager.usersList()){
                if(userRequest.getMale().isBlank() || userRequest.getAge()==0 || userRequest.getWeight()==0.0F || userRequest.getHeight()==0.0F || userRequest.getAim().isBlank() || userRequest.getWaterAmount()==0.0F || userRequest.getPhysicalActivity().isBlank() || userRequest.getBirthDate()==null){
                    call.respond(HttpStatusCode.BadRequest, "Not all fields are filled")
                }else{
                    if(incorrectChoice(userRequest.getMale(),maleList))
                        call.respond(HttpStatusCode.BadRequest, "Invalid input(choose \"male\" or \"female\")")
                    if(incorrectChoice(userRequest.getAim(),aimList))
                        call.respond(HttpStatusCode.BadRequest, "Invalid input(choose \"weight loss\", \"weight maintenance\", \"weight gain\")")
                    if(numberError(userRequest.getWeight(), weightRangeList[0], weightRangeList[1]) || numberError(userRequest.getHeight(),
                            heightRangeList[0], heightRangeList[1]) || numberError(userRequest.getWaterAmount(),
                                waterAmountRangeList[0], waterAmountRangeList[1]))
                        call.respond(HttpStatusCode.OK, "Data saved")
                }
            }else{
                call.respond(HttpStatusCode.Unauthorized, "You have to authorize")
            }
        }
    }
}

fun Application.registerRoutes() {
    routing {
        usersRouting()
    }
}


