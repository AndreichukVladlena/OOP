class ActualFoodItem(name: String) :FoodItem(name) {
    private var amount: Float=0.0F
    fun setItemAmount(number:Number){
        this.amount=number!!.toFloat()
    }
    fun getResultCalories(): Float {
        return this.calories * this.amount
    }

    fun getItemAmount():Float{
        return this.amount
    }
}