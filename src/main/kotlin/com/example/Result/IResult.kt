package com.example.Result

import com.example.Entities.User
import kotlinx.serialization.Contextual
import org.bson.types.ObjectId
import org.intellij.lang.annotations.Identifier

interface IResult {
    val user:User
    var userId: String
    val physicalActivityLevels: Map<String, String>
    var caloriesNorm:Float
    var normalWeight:Float
    var waterNorm:Float
    var physActivityNorm:String
//    val userId: String
    @Identifier
    @Contextual
    val id: ObjectId?


    fun calculateCaloriesNorm():Float
    fun calculateNormalWeight(): Float
    fun calculateWaterNorm(): Float
    fun calculatePhysActivityNorm(): String{
            val physicalActivityLevel:String="normal"
            return physicalActivityLevels[physicalActivityLevel].toString()
    }
}