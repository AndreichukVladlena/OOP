
import DBManagers.FoodManager
import org.bson.Document
import src.DBManagers.ActualFoodManager

class FoodTracker (private val result: Document){

    private var foodManager = FoodManager()
    private var actualFoodManager= ActualFoodManager()
    private var actualCalories: Float = this.setActualKilocalories()
    var userId:String = result["userId"].toString()
    var resultId:String = result["_id"].toString()

    fun getActualCalories():Float{
        return this.actualCalories
    }

    fun setActualKilocalories():Float{
        var totalCalories: Float = 0.0F
        for(item in actualFoodManager.getUsersFood(userId)){
            totalCalories += item["result calories"].toString().toFloat()
        }
        return totalCalories
    }

    fun caloriesNormDiff(): Float{
        return result["caloriesNorm"].toString().toFloat()-actualCalories
    }
}