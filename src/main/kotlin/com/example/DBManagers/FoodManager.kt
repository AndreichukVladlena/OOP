package DBManagers

import FoodItem
import com.example.DBManagers.DataBase
import com.example.Entities.User
import org.bson.Document
import org.bson.types.ObjectId

class FoodManager{
    private var dataBase = DataBase
    fun addFoodItem(item: FoodItem):Boolean{
        if (dataBase.isNameFieldExist("food items", "name", item.getItemName())){
            return false
        }else {
            dataBase.insert("food items", this.itemToDoc(item))
            return true
        }
    }

    fun setFoodItemData(id: String, item: FoodItem):Boolean{
        return if (dataBase.isExist("food items", id)){
            dataBase.update("food items", this.itemToDoc(item))
            true
        }else{
            false
        }
    }

    fun getItemData(id: String): Document?{
        return dataBase.get("food items", id)
    }

    fun removeFoodItem(id: String) :Boolean{
        if (dataBase.isExist("food items", id)) {
            dataBase.delete("food items", id)
            return true
        }
        return false
    }

    fun foodItemExists(item: FoodItem):Boolean{
        return dataBase.isNameFieldExist("food items", "name", item.getItemName())
    }

    fun userExists(user: User): Boolean{
        return dataBase.isNameFieldExist("user","username", user.getUsername()) && dataBase.isNameFieldExist("user","password", user.getPassword())
    }

    fun itemToDoc(item: FoodItem): Document {
        return Document(mapOf(
            "_id" to ObjectId(),
            "name" to item.getItemName(),
            "calories" to item.getItemCalories()
    }
}