
import com.example.DBManagers.DataBase
import kotlinx.serialization.Contextual
import kotlinx.serialization.Transient
import org.bson.Document
import org.bson.types.ObjectId
import org.intellij.lang.annotations.Identifier
import src.DBManagers.ActualFoodManager

class Tracker (private val result: Document){
    private var dataBase = DataBase

    @Identifier
    @Contextual
    @Transient
    var id: ObjectId? = null
    private var actualFoodManager= ActualFoodManager()
    private var actualCalories: Float = dataBase.getFieldValue("tracker", this.id.toString(), "actual calories").toString().toFloat()
    var userId:String = result["userId"].toString()
    private var actualWaterAmount : Float = dataBase.getFieldValue("tracker", this.id.toString(), "actual water amount").toString().toFloat()
//    var resultId:String = result["_id"].toString()


    fun getActualWaterAmount():Float{
        return dataBase.getFieldValue("tracker", this.id.toString(), "actual water amount").toString().toFloat()
    }
    fun addWaterGlass(){
        this.actualWaterAmount += getActualWaterAmount() + 0.25F
        dataBase.update("tracker", this.id.toString(), "water amount", this.actualWaterAmount)
        dataBase.update("tracker", this.id.toString(), "water diff", this.waterNormDiff())
    }

    fun removeWaterGlass(){
        this.actualWaterAmount= this.actualWaterAmount + .25F
        dataBase.update("tracker", this.id.toString(), "water amount", this.actualWaterAmount)
        dataBase.update("tracker", this.id.toString(), "water diff", this.waterNormDiff())
    }

    fun waterNormDiff():Float{
        return result["waterNorm"].toString().toFloat()-this.actualWaterAmount
    }

    fun getActualCalories():Float{
        this.setActualKilocalories()
        return dataBase.getFieldValue("tracker", this.id.toString(), "actual calories").toString().toFloat()
    }

    fun setActualKilocalories():Float{
        var totalCalories: Float = 0.0F
        for(item in actualFoodManager.getUsersFood(userId)){
            totalCalories += item["result calories"].toString().toFloat()
        }
        this.actualCalories=totalCalories
        dataBase.update("tracker", this.id.toString(), "actual calories", totalCalories)

        return totalCalories
    }

    fun caloriesNormDiff(): Float{
        this.setActualKilocalories()
        return result["caloriesNorm"].toString().toFloat()-actualCalories
    }
}