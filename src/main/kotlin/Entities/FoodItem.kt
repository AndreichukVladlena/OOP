open class FoodItem (private var name:String){
    protected var calories:Float=0.0F

    fun editItemCalories(number:Number?){
        this.calories= number!!.toFloat()
    }

    fun getItemName():String{
        return this.name
    }
    fun getItemCalories():Float{
        return this.calories
    }
}