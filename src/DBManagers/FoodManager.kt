package DBManagers
import FoodItem
open class FoodManager {
    private var foodItems = LinkedHashSet<FoodItem> ()

    fun addItem(foodItem: FoodItem){
        this.foodItems.add(foodItem)
    }

    fun removeItem(foodItem: FoodItem):Boolean{
        if (foodItemExists(foodItem)){
            foodItems.remove(foodItem)
            return true
        }
        else{return false}
    }

    fun foodItemExists(foodItem: FoodItem):Boolean{
        if (foodItem in foodItems){return true}
        else{return false}
    }
}