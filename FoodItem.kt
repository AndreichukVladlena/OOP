class FoodItem (private var name:String){
    private var calories:Float=0.0F
    private var amount: Float=0.0F
    fun editItemCalories(number:Number?){
        this.calories= number!!.toFloat()
    }

    fun setItemAmount(number:Number){
        this.amount=number!!.toFloat()
    }
    fun resultCalories(): Float {
        return this.calories * this.amount
    }

    fun getItemName():String{
        return this.name
    }
    fun getItemCalories():Float{
        return this.calories
    }

    fun getItemAmount():Float{
        return this.amount
    }
}