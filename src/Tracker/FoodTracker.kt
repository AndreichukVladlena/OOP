import DBManagers.FoodManager

class FoodTracker (private val result: IResult){
    private var foodManager = FoodManager()
    private var actualCalories: Float = 0.0F

    var actualFoodItems = LinkedHashSet<ActualFoodItem> ()

    fun getActualCalories():Float{
        return this.actualCalories
    }

    fun addFoodItem(item : ActualFoodItem){
        actualFoodItems.add(item)
        this.actualCalories+=item.getResultCalories()
        if(!foodManager.foodItemExists(item)){foodManager.addItem(item)}
    }

    fun removeFoodItem(name: String){
        for (item in actualFoodItems) {
            if (item.getItemName() == name) actualFoodItems.remove(item)
        }
    }

    fun calculateActualKilocalories():Float{
        var totalCalories: Float = 0.0F
        for (foodItem in actualFoodItems) {
            totalCalories += foodItem.getResultCalories()
        }
        return totalCalories
    }

    fun caloriesNormDiff(): Float{
        return result.caloriesNorm-actualCalories
    }

    fun getActualFoodList(): LinkedHashSet<ActualFoodItem> {
        return LinkedHashSet(this.actualFoodItems)
    }
}