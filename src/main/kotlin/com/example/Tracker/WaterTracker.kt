import org.bson.Document

class WaterTracker(private val result: Document) {
    private var actualWaterAmount : Float = 0.0F

    fun getActualWaterAmount():Float{
        return this.actualWaterAmount
    }
    fun addWaterGlass(){
        this.actualWaterAmount += 0.25F
    }

    fun removeWaterGlass(){
        this.actualWaterAmount-=0.25F
    }

    fun waterNormDiff():Float{
        return result["waterNorm"].toString().toFloat()-this.actualWaterAmount
    }
}