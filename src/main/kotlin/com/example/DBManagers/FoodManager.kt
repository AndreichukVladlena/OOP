package DBManagers

import FoodItem
import com.example.DBManagers.DataBase
import org.bson.Document
import org.bson.types.ObjectId

class FoodManager{
    private var dataBase = DataBase
    fun addFoodItem(item: FoodItem):String{
        var doc = Document()

        doc["name"]=item.getItemName()
        doc["calories"]=item.getItemCalories()

        return dataBase.insert("food item", doc)
    }

    fun getItemData(name: String): Document?{
        return dataBase.getByFieldValue("food item", "name", name)
    }

    fun itemExists(name: String):Boolean{
        return dataBase.getByFieldValue("food item", "name", name)!=null
    }

    fun removeFoodItem(name: String) :Boolean{
        val item = this.getItemData(name)
        val itemId:ObjectId
        if (item!=null && item.containsKey("_id")){
            itemId = ObjectId(item["_id"].toString())
            dataBase.delete("food item", itemId.toString())
            return true
        }else return false
    }
}