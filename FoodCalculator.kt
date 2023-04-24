class FoodCalculator (private val result: IResult){
    private var actualCalories: Float = 0.0F
    private var actualWaterAmount : Float = 0.0F

    var actualFoodItems = LinkedHashSet<FoodItem> ()

    fun addFoodItem(item : FoodItem){
        actualFoodItems.add(item)
        this.actualCalories+=item.resultCalories()
    }

    fun removeFoodItem(name: String){
        for (item in actualFoodItems) {
            if (item.getItemName() == name) actualFoodItems.remove(item)
        }
    }

    fun addWaterGlass(){
        actualWaterAmount += 0.25F
    }

    fun calculateActualKilocalories():Float{
        var totalCalories: Float = 0.0F
        for (foodItem in actualFoodItems) {
            totalCalories += foodItem.resultCalories()
        }
        return totalCalories
    }

    fun caloriesNormDiff(): Float{
        return result.caloriesNorm-actualCalories
    }

    fun waterNormDiff():Float{
        return result.waterNorm-actualWaterAmount
    }

    fun getActualFoodList(): LinkedHashSet<FoodItem> {
        return LinkedHashSet(this.actualFoodItems)
    }
}