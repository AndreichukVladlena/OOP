
import com.example.Entities.User
import com.example.Result.IResult
import com.example.const.aimList
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.intellij.lang.annotations.Identifier


@Serializable
class ChildResult(override val user: User) : IResult {
    @Transient
    override val physicalActivityLevels = mapOf(
        "low" to "Less than 60 minutes of moderate to vigorous-intensity physical activity per day.",
        "normal" to "At least 60 minutes of moderate to vigorous-intensity physical activity per day.",
        "high" to "More than 60 minutes of moderate to vigorous-intensity physical activity per day."
    )

    override var caloriesNorm:Float=calculateCaloriesNorm()
    override var normalWeight:Float=calculateNormalWeight()
    override var waterNorm:Float=calculateWaterNorm()
    override var physActivityNorm:String=calculatePhysActivityNorm()
    override var userId: String = user.id.toString()
    @Identifier
    @Contextual
    @Transient
    override val id: ObjectId? = null
    override fun calculateNormalWeight(): Float {
        return (2 * (user.getHeight() + 5)).toFloat()
    }

    override fun calculateWaterNorm(): Float {
        return ((user.getWeight() * 50)/1000).toFloat()
    }

    override fun calculateCaloriesNorm(): Float {
        var calories: Float = (((10 * user.getWeight()) + (6.25 * user.getHeight()) - (5 * user.getAge()) + 5) * 1.2).toFloat()
        when(user.getAim()){
            aimList[0]->{
                return calories * 0.8F
            }
            aimList[1]->{
                return calories
            }
            aimList[2]->{
                return calories * 1.2F
            }
            else->{
                return 0.0F
            }
        }
    }
}