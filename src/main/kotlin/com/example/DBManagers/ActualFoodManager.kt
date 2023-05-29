package src.DBManagers

import ActualFoodItem
import DBManagers.FoodManager
import com.example.DBManagers.DataBase
import org.bson.Document


class ActualFoodManager{
    private var dataBase = DataBase
    private var foodManager = FoodManager()
    fun addActualFoodItem(item: ActualFoodItem):String{
        var doc = Document()

        doc["userId"]=item.getUserId()
        doc["name"] = item.getActualItemName()
        doc["calories"] = item.getActualItemCalories()
        doc["result calories"] = item.getResultCalories()
        doc ["amount"] = item.getItemAmount()
        doc ["result calories"] = item.getResultCalories()

        return dataBase.insert("actual food items", doc)
    }

    fun setItemAmount(id:String, name: String, amount:Float):Boolean{
        val usersFood = dataBase.getSeveralByFieldValue("actual food items", "userId", id)
        for (item in usersFood){
            if (dataBase.getByFieldValue("actual food items", "name", name)!=null) {
                dataBase.update("actual food items", item["_id"].toString(), "amount", amount)
                val resultCalories = amount * item["calories"].toString().toFloat()
                dataBase.update("actual food items", item["_id"].toString(),"result calories", resultCalories)
                return true
            }
        }
        return false
    }

    fun isUserItemExists(id:String, name: String):Boolean{
        val usersFood = dataBase.getSeveralByFieldValue("actual food items", "userId", id)
        for (item in usersFood){
            if (dataBase.getByFieldValue("actual food items", "name", name)!=null) return true
        }
        return false
    }

//    fun getActualFoodItemData(id: String): Document?{
//        return dataBase.get("actual food items", id)
//    }
//
//    fun removeActualFoodItem(id: String) :Boolean{
//        if (dataBase.isExist("actual food items", id)) {
//            dataBase.delete("actual food items", id)
//            return true
//        }
//        return false
//    }
//
//    fun actualFoodItemExists(item: ActualFoodItem):Boolean{
//        return dataBase.isNameFieldExist("actual food items", "name", item.getActualItemName())
//    }
//
//    fun actualItemToDoc(item: ActualFoodItem): Document {
//        return Document(mapOf(
//            "_id" to ObjectId(),
//            "userId" to item.getUserId(),
//            "name" to item.getActualItemName(),
//            "creation date" to item.getDate(),
//            "calories" to item.getDate(),
//            "amount" to item.getItemAmount(),
//            "result calories" to item.getResultCalories()))
//    }
//
//    fun getUsersFood(id:String): FindIterable<Document> {
//        return dataBase.getItemsByField("actual food items", "userId", id)
//    }
//
//    fun findItemByName(id: String, name: String):String{
//        val usersFood = this.getUsersFood(id)
//        for (item in usersFood){
//            if (item["name"]==name) return item["_id"].toString()
//        }
//    return ""
//    }

}