package DBManagers

import FoodItem
import com.example.DBManagers.DataBase
import com.mongodb.client.MongoCollection
import org.bson.Document
import org.bson.types.ObjectId

class FoodManager{
    private var dataBase = DataBase
    fun addFoodItem(item: FoodItem):String{
        var doc = Document()

        doc["name"]=item.getItemName()
        doc["calories"]=item.getItemCalories()

        return dataBase.insert("food items", doc)
    }

    fun getItemData(name: String): Document?{
        return dataBase.getByFieldValue("food items", "name", name)
    }

    fun itemExists(name: String):Boolean{
        return dataBase.getByFieldValue("food items", "name", name)!=null
    }

    fun removeFoodItem(name: String) :Boolean{
        val item = this.getItemData(name)
        val itemId:ObjectId
        if (item!=null && item.containsKey("_id")){
            itemId = ObjectId(item["_id"].toString())
            dataBase.delete("food items", itemId.toString())
            return true
        }else return false
    }

    fun getAll(): MongoCollection<Document> {
        return dataBase.getDB("food items")
    }
}