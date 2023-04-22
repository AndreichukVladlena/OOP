interface IResult {
    val physicalActivityLevels: Map<String, String>
    var caloriesNorm:Float
    fun getCaloriesNorm():Float{
        return this.caloriesNorm
    }
    fun calculateCaloriesNorm()
    fun calculateNormalWeight(): Float
    fun calculateWaterNorm(): Float
    fun calculatePhysActivityNorm(): String{
            val physicalActivityLevel:String="normal"
            return physicalActivityLevels[physicalActivityLevel].toString()
    }

//    fun getPhysActivityTips(): String
//    fun getAimTips(): String

//    fun getWaterTips(): String
}