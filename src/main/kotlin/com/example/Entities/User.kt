import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlinx.serialization.Serializable
import kotlinx.serialization.Contextual

@Serializable
data class User(val username:String, var password:String) {
//    private val name:String = username
//    private var password:String = pass
    private var male: String = "null"
    private var age: Int = 0
    private var weight: Float = 0.0F
    private var height: Float = 0.0F
    private var aim: String = ""
    private var waterAmount: Float = 0.0F
    private var physicalActivity: String = ""
    @Contextual
    private var birthDate: LocalDate? =null

    init {
        require(username.isNotBlank()) { "Username must not be blank" }
        require(password.isNotBlank()) { "Password must not be blank" }
    }

    fun getName():String{
        return this.username
    }

    fun getPassword():String{
        return this.password
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