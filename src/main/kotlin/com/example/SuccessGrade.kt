import com.example.const.physicalActivityNorm
class SuccessGrade(private val result: IResult, private val foodTracker: FoodTracker, private val physicalActivityTracker: PhysicalActivityTracker, private val waterTracker: WaterTracker) {
    fun countSuccess():Float{
        return (waterTracker.getActualWaterAmount()/result.waterNorm + foodTracker.getActualCalories()/result.caloriesNorm + physicalActivityTracker.getActualPhysicalActivityMinutes()/physicalActivityNorm)/3
    }
}