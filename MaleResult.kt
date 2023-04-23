import IResult

class MaleResult(private val height: Float, private val age: Int, private val weight: Float) : IResult {
    override var caloriesNorm: Float = 0.0F
    override val physicalActivityLevels = mapOf(
        "low" to "Less than 30 minutes of moderate-intensity physical activity per day.",
        "normal" to "30 minutes of moderate-intensity physical activity per day.",
        "high" to "More than 60 minutes of moderate-intensity physical activity per day."
    )

    override fun calculateNormalWeight(): Float{
        return ((height - 100) * 0.85).toFloat()
    }

    override fun calculateWaterNorm(): Float{
        return (((weight * 40) + (age * 10) + 1000)/1000).toFloat()
    }

    override fun calculateCaloriesNorm() {
        this.caloriesNorm = (((10 * weight) + (6.25 * height) - (5 * age) + 5) * 1.5).toFloat()
    }
}