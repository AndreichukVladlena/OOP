class WaterTracker(private val result: IResult) {
    private var actualWaterAmount : Float = 0.0F

    fun addWaterGlass(){
        this.actualWaterAmount += 0.25F
    }

    fun removeWaterGlass(){
        this.actualWaterAmount-=0.25F
    }

    fun waterNormDiff():Float{
        return result.waterNorm-this.actualWaterAmount
    }
}