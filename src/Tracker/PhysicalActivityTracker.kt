class PhysicalActivityTracker(private val result: IResult) {
    private var actualPhysicalActivityMinutes : Int = 0

    fun getActualPhysicalActivityMinutes():Int{
        return this.actualPhysicalActivityMinutes
    }

    fun editPhysicalActivityMinutes(minutes:Int){
        this.actualPhysicalActivityMinutes = minutes
    }
    fun physActivityNormDiff():Int{
        return physicalActivityNorm - this.actualPhysicalActivityMinutes
    }
}