interface IResult {
    val physicalActivityLevels: Map<String, String>
    var caloriesNorm:Float
    var normalWeight:Float
    var waterNorm:Float
    var physActivityNorm:String

    fun calculateCaloriesNorm():Float
    fun calculateNormalWeight(): Float
    fun calculateWaterNorm(): Float
    fun calculatePhysActivityNorm(): String{
            val physicalActivityLevel:String="normal"
            return physicalActivityLevels[physicalActivityLevel].toString()
    }
}