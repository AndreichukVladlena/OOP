class MaleResult(private val user: User) : IResult {
    override val physicalActivityLevels = mapOf(
        "low" to "Less than 30 minutes of moderate-intensity physical activity per day.",
        "normal" to "30 minutes of moderate-intensity physical activity per day.",
        "high" to "More than 60 minutes of moderate-intensity physical activity per day."
    )

    override var caloriesNorm:Float=calculateCaloriesNorm()
    override var normalWeight:Float=calculateNormalWeight()
    override var waterNorm:Float=calculateWaterNorm()
    override var physActivityNorm:String=calculatePhysActivityNorm()

    override fun calculateNormalWeight(): Float{
        return ((user.getHeight() - 100) * 0.85).toFloat()
    }

    override fun calculateWaterNorm(): Float{
        return (((user.getWeight() * 40) + (user.getAge() * 10) + 1000)/1000).toFloat()
    }

    override fun calculateCaloriesNorm() :Float{
        var calories : Float = (((10 * user.getWeight()) + (6.25 * user.getHeight()) - (5 * user.getAge()) + 5) * 1.5).toFloat()
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