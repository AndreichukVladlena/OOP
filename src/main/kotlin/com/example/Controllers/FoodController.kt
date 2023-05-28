package com.example.Controllers

import io.ktor.server.routing.*


fun Route.actualFoodRouting() {
//    var actualFoodManager = ActualFoodManager()
//    var foodManager = FoodManager()
//
//
//    route("api/foodManager/{user_id}") {
//        get("/get") {
//            val food = actualFoodManager.getUsersFood(call.parameters["user_id"].toString())
//            if(food!=null) {
//                for (item in food) {
//                    call.respond(HttpStatusCode.OK, item.toJson())
//                }
//            }
////            if(food!=null) call.respond(HttpStatusCode.OK, food.toJson())
//            else call.respond(HttpStatusCode.BadRequest, "Empty")
//        }
//        post("/addItem") {
//            val item = call.receive<FoodItem>()
//
//            val actualFoodItem = ActualFoodItem(item, call.parameters["user_id"].toString(), 0.0F)
//            if (actualFoodManager.addActualFoodItem(actualFoodItem)) call.respond(HttpStatusCode.OK, "Success")
//            else call.respond(HttpStatusCode.BadRequest, "Item exists, you should update it's info")
//        }
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

