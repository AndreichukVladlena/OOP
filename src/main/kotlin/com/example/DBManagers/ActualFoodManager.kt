package src.DBManagers

import ActualFoodItem
import DBManagers.FoodManager
import com.example.DBManagers.DataBase
import org.bson.Document
import org.bson.types.ObjectId

//написать функцию изменения парметров item

class ActualFoodManager{
    private var dataBase = DataBase
    private var foodManager=FoodManager()
    fun addActualFoodItem(item: ActualFoodItem):Boolean{
        if (dataBase.isNameFieldExist("food items", "name", item.getActualItemName())){
            if (dataBase.isNameFieldExist("actual food items", "name", item.getActualItemName()))
            return false
            else{
                dataBase.insert("actual food items", this.actualItemToDoc(item))
                return true
            }
        }else {
            dataBase.insert("food items", this.actualItemToDoc(item))
            dataBase.insert("actual food items", this.actualItemToDoc(item))
            return true
        }
    }

    fun editActualFoodItemData(id: String, item: ActualFoodItem):Boolean{
        return if (dataBase.isExist("actual food items", id)){
            dataBase.update("actual food items", this.actualItemToDoc(item))
            true
        }else{
            false
        }
    }

    fun getActualFoodItemData(id: String): Document?{
        return dataBase.get("actual food items", id)
    }

    fun removeActualFoodItem(id: String) :Boolean{
        if (dataBase.isExist("actual food items", id)) {
            dataBase.delete("actual food items", id)
            return true
        }
        return false
    }

    fun actualFoodItemExists(item: ActualFoodItem):Boolean{
        return dataBase.isNameFieldExist("actual food items", "name", item.getActualItemName())
    }

    fun actualItemToDoc(item: ActualFoodItem): Document {
        return Document(mapOf(
            "_id" to ObjectId(),
            "name" to item.getActualItemName(),
            "creation date" to item.getDate(),
            "calories" to item.getDate(),
            "amount" to item.getItemAmount(),
            "result calories" to item.getResultCalories()))
    }
}