package src.DBManagers

import ActualFoodItem
import DBManagers.FoodManager
import com.example.DBManagers.DataBase
import com.mongodb.client.FindIterable
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
            if (item["name"].toString()==name) return true
        }
        return false
    }

    fun getUsersFood(id: String): FindIterable<Document> {
        return dataBase.getSeveralByFieldValue("actual food items", "userId", id)
    }
}