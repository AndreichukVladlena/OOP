
import com.example.Result.IResult

class FoodTracker (private val result: IResult){
//    private var foodManager = FoodManager()
//    private var actualFoodManager=ActualFoodManager()
//    private var actualCalories: Float = 0.0F
//
//
//    fun getActualCalories():Float{
//        return this.actualCalories
//    }
//
//    fun addFoodItem(item : ActualFoodItem){
//        actualFoodManager.addItem(item)
//        this.actualCalories+=item.getResultCalories()
//        if(!foodManager.foodItemExists(item)){foodManager.addItem(item)}
//    }
//
//    fun removeFoodItem(name: String){
//        for (item in actualFoodManager.getCollection()){
//            if(item.getItemName()==name)actualFoodManager.removeItem(item)
//        }
//    }
//
//    fun calculateActualKilocalories():Float{
//        var totalCalories: Float = 0.0F
//        for(item in actualFoodManager.getCollection()){
//            totalCalories += item.getResultCalories()
//        }
//        return totalCalories
//    }
//
//    fun caloriesNormDiff(): Float{
//        return result.caloriesNorm-actualCalories
//    }
//
//    fun getActualFoodList(): LinkedHashSet<ActualFoodItem> {
//        return actualFoodManager.getCollection()
//    }
}