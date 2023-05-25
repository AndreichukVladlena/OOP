import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.intellij.lang.annotations.Identifier

@Serializable
open class FoodItem (private var name:String, private var calories: Float){
//    var calories:Float=0.0F
    @Identifier
    @Contextual
    @Transient
    val id: ObjectId? = null

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