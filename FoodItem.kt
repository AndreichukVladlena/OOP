class FoodItem {
    protected var calories: Float = 0.0F
    protected var amount: Int = 0

    fun resultCalories() : Float {
        return calories * amount
    }
}