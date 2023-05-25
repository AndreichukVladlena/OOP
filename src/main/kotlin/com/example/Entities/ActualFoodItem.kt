
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.intellij.lang.annotations.Identifier

@Serializable
data class ActualFoodItem(private val item: FoodItem, private val userId:String, private var amount: Float, private val creationDate:LocalDate=LocalDate(2023, 5, 30)) {
//    private var amount: Float=0.0F
    @Identifier
    @Contextual
    @Transient
    val id: ObjectId? = null
    fun setItemAmount(number:Number){
        this.amount=number!!.toFloat()
    }
    fun getResultCalories(): Float {
        return this.getActualItemCalories() * this.amount
    }

    fun getItemAmount():Float{
        return this.amount
    }

    fun getUserId():String{
        return this.userId
    }

    fun getActualItemName():String{
        return item.getItemName()
    }

    fun getActualItemCalories():Float{
        return item.getItemCalories()
    }

    fun getDate():LocalDate{
        return this.creationDate
    }
}