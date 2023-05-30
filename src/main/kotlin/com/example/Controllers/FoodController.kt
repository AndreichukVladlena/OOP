package com.example.Controllers

import ActualFoodItem
import DBManagers.FoodManager
import FoodItem
import com.example.DBManagers.TrackerManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bson.Document
import src.DBManagers.ActualFoodManager

fun Route.actualFoodRouting() {
    var actualFoodManager = ActualFoodManager()
    var foodManager = FoodManager()
    var trackerManager = TrackerManager()


    route("api/foodManager/{user_id}") {
        get("/getFoodItems") {
            val usersFood = actualFoodManager.getUsersFood(call.parameters["user_id"].toString())
            val doc = Document()
            var s:String=""
            for (item in usersFood){
                s+=item["name"].toString()+"\n"
            }
            call.respond(s)
        }

        post("/addItem") {
            val userId = call.parameters["user_id"].toString()
            val name = call.receive<String>()
            val itemDoc = foodManager.getItemData(name)

            if (actualFoodManager.isUserItemExists(userId, name)) return@post call.respond("This item in your today list already exists, you should just edit amount")

            if (foodManager.itemExists(name)) {
                if (itemDoc != null && itemDoc.containsKey("name") && itemDoc.containsKey("calories")) {
                    val foodItem = FoodItem(itemDoc["name"].toString(), itemDoc["calories"].toString().toFloat())
                    val actualFoodItem = ActualFoodItem(foodItem, userId, 1.0F)
                    actualFoodManager.addActualFoodItem(actualFoodItem)
                    call.respond(HttpStatusCode.OK, "Success")
                }else call.respond("Not Found")
            } else call.respond(HttpStatusCode.BadRequest, "Item doesn't exist")

            trackerManager.setActualCalories(userId)
        }

        post("/editAmountOfItem/{item_name}") {
            val userId = call.parameters["user_id"].toString()
            val itemName = call.parameters["item_name"].toString()
            val amount = call.receive<String>().toFloat()

            if (actualFoodManager.isUserItemExists(userId, itemName)) {
                actualFoodManager.setItemAmount(userId, itemName, amount)
                call.respond("Success")
            } else call.respond("You don't have such item")

            trackerManager.setActualCalories(userId)
        }

        post("/addWater") {
            trackerManager.addWater(call.parameters["user_id"].toString())

            val answer = trackerManager.getTracker(call.parameters["user_id"].toString())
            if(answer!=null) call.respond(HttpStatusCode.OK, answer.toJson())
            else call.respond(HttpStatusCode.BadRequest, "Error")
        }

        post("/removeWater") {
            trackerManager.removeWater(call.parameters["user_id"].toString())

            val answer = trackerManager.getTracker(call.parameters["user_id"].toString())
            if(answer!=null) call.respond(HttpStatusCode.OK, answer.toJson())
            else call.respond(HttpStatusCode.BadRequest, "Error")        }

        post("/setPhysActivity"){
            val min = call.receive<String>().toFloat()
            if (!trackerManager.setPhysicalActivity(call.parameters["user_id"].toString(), min.toFloat())) call.respond("Error")

            val answer = trackerManager.getTracker(call.parameters["user_id"].toString())
            if(answer!=null) call.respond(HttpStatusCode.OK, answer.toJson())
            else call.respond(HttpStatusCode.BadRequest, "Error")
        }

    }
}

