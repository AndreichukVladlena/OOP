class FoodCalculator (private val result: IResult){
    private var actualCalories: Float = 0.0F
    private var actualWaterAmount : Float = 0.0F

    val actualFoodItems : LinkedHashSet<FoodItem> =(setOf<FoodItem>() as LinkedHashSet<FoodItem>)

    fun addFoodItem(item : FoodItem){
        actualFoodItems.add(item)
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

    fun normDiff(): Float{
        return result.getKilocaloriesNorm()-actualCalories
    }
}