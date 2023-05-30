package com.example.Controllers

import DBManagers.UserManager
import InterfaceService
import com.example.DBManagers.ResultManager
import com.example.DBManagers.TrackerManager
import com.example.Entities.User
import com.example.ErrorTracker.incorrectChoice
import com.example.ErrorTracker.numberError
import com.example.Result.IResult
import com.example.const.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.usersRouting() {
    var userManager = UserManager()
    var resultManager = ResultManager()
    var trackerManager = TrackerManager()


    route("api/users") {
        get("/get/{user_id}") {
            val userData = userManager.getUserData(call.parameters["user_id"].toString())
            if(userData!=null) call.respond(HttpStatusCode.OK, userData.toJson())
            else call.respond(HttpStatusCode.BadRequest, "Not found ${call.parameters["user_id"]}")
        }
        post("/register") {
            val userRequest = call.receive<User>()
            call.respond(HttpStatusCode.OK, "Success\n${userManager.addUser(userRequest)}")
        }

        post("/login") {
            val userRequest = call.receive<User>()

            if (userManager.login(userRequest)) {
                call.respond(HttpStatusCode.OK, "Login successful")
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Invalid username or password")
            }
        }
        post("/setData/{user_id}"){
            var userRequest = call.receive<User>()
            val userId:String = call.parameters["user_id"].toString()

            if(!incorrectChoice(userRequest.getMale(), maleList) || !numberError(userRequest.getWeight(), weightRangeList[0], weightRangeList[1])
                || !numberError(userRequest.getHeight(), heightRangeList[0], heightRangeList[1]) ||
                !incorrectChoice(userRequest.getAim(), aimList) || !numberError(userRequest.getWaterAmount(), waterAmountRangeList[0],
                    waterAmountRangeList[1])) call.respond(HttpStatusCode.BadRequest, "Input Error")

            if (userManager.userExists(userId)){
                userManager.setUserData(userId, userRequest)
                call.respond(HttpStatusCode.OK, "Success\n${userManager.getUserData(userId)!!.toJson()}")
            }else call.respond(HttpStatusCode.BadRequest, "User not found")

            val iService = InterfaceService()
            var result: IResult? = iService.chooseResult(userRequest)
            result!!.userId=userId


            if(resultManager.resultExists(userId))resultManager.setResultData(userId, result)
            else resultManager.addResult(result)

            if(!trackerManager.trackerExists(userId))trackerManager.addTracker(userId)

        }

        get("/getResult/{user_id}"){
            val resDoc = resultManager.getResult(call.parameters["user_id"].toString())

            if (resDoc!=null) call.respond(HttpStatusCode.OK, resDoc.toJson())
            else call.respond(HttpStatusCode.BadRequest, "Not found")
        }

        get("/getTracker/{user_id}"){
            val userId = call.parameters["user_id"].toString()
            if(trackerManager.trackerExists(userId)) call.respond(trackerManager.getTracker(call.parameters["user_id"].toString())!!.toJson())
            else call.respond("You should create tracker")
        }

        delete("/delete/{user_id}") {
            if (userManager.removeUser(call.parameters["user_id"].toString()))call.respond(HttpStatusCode.OK, "Deleted")
            else call.respond(HttpStatusCode.BadRequest, "Not found")
        }
    }
}

