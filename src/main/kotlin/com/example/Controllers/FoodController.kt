package com.example.Controllers

import ActualFoodItem
import DBManagers.FoodManager
import FoodItem
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import src.DBManagers.ActualFoodManager


fun Route.actualFoodRouting() {
    var actualFoodManager = ActualFoodManager()
    var foodManager = FoodManager()


    route("api/foodManager/{user_id}") {
        get("/getFoodItems") {
            call.respond(foodManager.getAll())
        }

        post("/addItem") {
            val userId = call.parameters["user_id"].toString()
            val name = call.receive<String>()
            val itemDoc = foodManager.getItemData(name)

            if(actualFoodManager.isUserItemExists(userId, name)) return@post call.respond("This item in your today list already exists, you should just edit amount")

            if(foodManager.itemExists(name)) {
                if (itemDoc != null && itemDoc.containsKey("name") && itemDoc.containsKey("calories")) {
                    val foodItem = FoodItem(itemDoc["name"].toString(), itemDoc["calories"].toString().toFloat())
                    val actualFoodItem = ActualFoodItem(foodItem, userId, 1.0F)
                    actualFoodManager.addActualFoodItem(actualFoodItem)
                    call.respond(HttpStatusCode.OK, "Success")
                }
            }else call.respond(HttpStatusCode.BadRequest, "Item doesn't exist")
        }

        post("/editAmountOfItem/{item_name}"){
            val userId = call.parameters["user_id"].toString()
            val itemName = call.parameters["item_name"].toString()
            val amount = call.receive<Float>()

            if (actualFoodManager.isUserItemExists(userId, itemName)){
                actualFoodManager.setItemAmount(userId, itemName, amount)
                call.respond("Success")
            }else call.respond("You don't have such item")
        }
//
//        post("/editAmountOfItem"){
//            val itemName = call.receive<String>()
//            val currItemId = actualFoodManager.findItemByName(call.parameters["user_id"].toString(), itemName)
//            if(currItemId!=""){
//                val newAmount:Float = call.receive<Number>().toFloat()
//                val currUsersDoc = actualFoodManager.getActualFoodItemData(currItemId)
//                currUsersDoc?.replace("amount", newAmount)
//                call.respond(HttpStatusCode.OK, "Sucess")
//            }else call.respond(HttpStatusCode.BadRequest, "Not found")
//        }
//        get("/get/{item_id}"){
//        }
//        delete("/delete") {
//            val itemName = call.receive<String>()
//            if (actualFoodManager.removeActualFoodItem(actualFoodManager.findItemByName(call.parameters["user_id"].toString(), itemName)))
//                call.respond(HttpStatusCode.OK, "Success")
//            else
//                call.respond(HttpStatusCode.BadRequest, "NotFound")
////            val nameRequest = call.receive<String>()
////            val food = actualFoodManager.getUsersFood(call.parameters["user_id"].toString())
////            if (food != null){
////                for (item in food){
////                    if (item.is)
////                }
////            }
////            if (actualFoodManager.)
////            if (actualFoodManager.removeUser(call.parameters["user_id"].toString()))call.respond(HttpStatusCode.OK, "Deleted")
////            else call.respond(HttpStatusCode.BadRequest, "Not found")
//        }
//    }
    }
}

