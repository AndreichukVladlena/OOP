package com.example.Entities

import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.bson.types.ObjectId
import org.intellij.lang.annotations.Identifier
import kotlin.math.absoluteValue

@Serializable
data class User(private val username:String, private var password:String) {

    private var male: String = "null"
    private var age: Int = 0
    private var weight: Float = 0.0F
    private var height: Float = 0.0F
    private var aim: String = ""
    private var waterAmount: Float = 0.0F
    private var physicalActivity: String = ""
    private var birthDate: LocalDate? = null
    @Identifier
    @Contextual
    @Transient
    var id: ObjectId? = null
    init {
        require(username.isNotBlank()) { "Username must not be blank" }
        require(password.isNotBlank()) { "Password must not be blank" }
    }

    fun getUsername():String{
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
        this.age= this.birthDate!!.minus(LocalDate(2023,5,23)).years.absoluteValue
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
        this.birthDate = LocalDate(year,month,day)
    }

}