class ChildResult(private val height: Double, private val age: Int, private val weight: Double) : IResult {
    override var caloriesNorm: Float = 0.0F
    override val physicalActivityLevels = mapOf(
        "low" to "Less than 60 minutes of moderate to vigorous-intensity physical activity per day.",
        "normal" to "At least 60 minutes of moderate to vigorous-intensity physical activity per day.",
        "high" to "More than 60 minutes of moderate to vigorous-intensity physical activity per day."
    )
    override fun calculateNormalWeight(): Float {
        return (2 * (height + 5)).toFloat()
    }

    override fun calculateWaterNorm(): Float {
        return ((weight * 50)/1000).toFloat()
    }

    override fun calculateCaloriesNorm() {
        this.caloriesNorm = (((10 * weight) + (6.25 * height) - (5 * age) + 5) * 1.2).toFloat()
    }
}