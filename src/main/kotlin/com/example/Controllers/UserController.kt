package com.example.Controllers

import DBManagers.UserManager
import com.example.DBManagers.ResultManager
import com.example.Entities.User
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.usersRouting() {
    var userManager = UserManager()
    var resultManager = ResultManager()
//    val test = User("fghj", "ferff")
//    test.setBirthDate(2004, 6, 27)
//    userManager.addUser(test)
    route("api/users") {
        get("/get/{user_id}") {
            val userData = userManager.getUserData(call.parameters["user_id"].toString())
            if(userData!=null) call.respond(HttpStatusCode.OK, userData.toJson())
            else call.respond(HttpStatusCode.BadRequest, "Not found ${call.parameters["user_id"]}")
        }
        post("/register") {
            val userRequest = call.receive<User>()
            call.respond(HttpStatusCode.OK, "Success${userManager.addUser(userRequest)}")
        }

        post("/login") {
            val userRequest = call.receive<User>()

            if (userManager.usernameExists(userRequest)) {
                call.respond(HttpStatusCode.OK, "Login successful")
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Invalid username or password")
            }
        }
        post("/setData/{user_id}"){
            var userRequest = call.receive<User>()
            val userId:String = call.parameters["user_id"].toString()

            if (userManager.userExists(userId)){
                userManager.setUserData(userId, userRequest)
                call.respond(HttpStatusCode.OK, "Success\n${userManager.getUserData(userId)!!.toJson()}")
            }else call.respond(HttpStatusCode.BadRequest, "User not found")

//
//                val iService = InterfaceService()
//                var result:IResult? = iService.chooseResult(userRequest)
//                result!!.userId=userId
//
//                if (resultManager.isResultExists(userId)){
////                    if(resultManager.updateResult(userId, result)) call.respond(HttpStatusCode.OK, "true")
////                    else call.respond(HttpStatusCode.BadRequest, "false")
//                    if (resultManager.setResultData(userId, result))call.respond(HttpStatusCode.OK, "Exists\n${resultManager.getUserResult(userId)?.toJson()}")
//                    else call.respond(HttpStatusCode.BadRequest, "false")
//                }else {
//                    resultManager.addResult(result)
//                    call.respond(HttpStatusCode.OK, "Success ${answer!!.toJson()}\n${resultManager.getUserResult(userId)?.toJson()}")}

//            }else {
//                call.respond(HttpStatusCode.BadRequest, "Not found")
//            }
        }
        delete("/delete/{user_id}") {
            if (userManager.removeUser(call.parameters["user_id"].toString()))call.respond(HttpStatusCode.OK, "Deleted")
            else call.respond(HttpStatusCode.BadRequest, "Not found")
        }
    }
}

