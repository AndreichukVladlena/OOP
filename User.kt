val aim = listOf("weight loss", "weight maintenance", "weight gain")
class User() {

    private var male: String = ""
    private var age: Int = 0
    private var weight: Float = 0.0F
    private var height: Float = 0.0F
    private var aim: String = ""
    private var waterAmount: Float = 0.0F
    private var physicalActivity: String = ""
    // геттеры и сеттеры
    fun getMale(): String {
        return male
    }

    fun setMale(male: String) {
        this.male = male
    }

    fun getAge(): Int {
        return age
    }

    fun setAge(age: Int) {
        this.age = age
    }

    fun getWeight(): Float {
        return weight
    }

    fun setWeight(weight: Float) {
        this.weight = weight
    }

    fun getHeight(): Float {
        return height
    }

    fun setHeight(height: Float) {
        this.height = height
    }

    fun getAim(): String {
        return aim
    }

    fun setAim(aim: String) {
        this.aim = aim
    }

    fun getWaterAmount(): Float {
        return waterAmount
    }

    fun setWaterAmount(waterAmount: Float) {
        this.waterAmount = waterAmount
    }

    fun getPhysicalActivity(): String {
        return physicalActivity
    }

    fun setPhysicalActivity(physicalActivity: String) {
        this.physicalActivity = physicalActivity
    }

}