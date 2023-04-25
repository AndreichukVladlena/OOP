import java.time.LocalDate
import java.time.Month
import java.time.Year
import java.time.temporal.ChronoUnit
class User(username:String, pass:String) {
    private var name:String = username
    private var password:String = pass
    private var male: String = "null"
    private var age: Int = 0
    private var weight: Float = 0.0F
    private var height: Float = 0.0F
    private var aim: String = ""
    private var waterAmount: Float = 0.0F
    private var physicalActivity: String = ""
    private var birthDate: LocalDate?=null

    fun getName():String{
        return this.name
    }

    fun getMale(): String {
        return this.male
    }

    fun setMale(male: String) {
        this.male = male
    }

    fun getAge(): Int {
        return this.age
    }

    fun setAge() {
        this.age=ChronoUnit.YEARS.between(this.birthDate,LocalDate.now()).toInt()
    }

    fun getWeight(): Float {
        return this.weight
    }

    fun setWeight(weight: Float) {
        this.weight = weight
    }

    fun getHeight(): Float {
        return this.height
    }

    fun setHeight(height: Float) {
        this.height = height
    }

    fun getAim(): String {
        return this.aim
    }

    fun setAim(aim: String) {
        this.aim = aim
    }

    fun getWaterAmount(): Float {
        return this.waterAmount
    }

    fun setWaterAmount(waterAmount: Float) {
        this.waterAmount = waterAmount
    }

    fun getPhysicalActivity(): String {
        return this.physicalActivity
    }

    fun setPhysicalActivity(physicalActivity: String) {
        this.physicalActivity = physicalActivity
    }

    fun getBirthDate():LocalDate?{
        return this.birthDate
    }

    fun setBirthDate(year: Int,month:Int,day:Int){
        this.birthDate = LocalDate.of(year,month,day)
    }

}