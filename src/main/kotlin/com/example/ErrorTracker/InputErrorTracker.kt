package com.example.ErrorTracker
import kotlin.collections.List
import IResult


    fun registrationResult(flag: Boolean): String {
        if (flag) {
            return "You have successfully authenticated!"
        } else {
            return "User with the same username is already exists!"
        }
    }

    fun incorrectChoice (answer: String, list: List<Any>):Boolean {
        return answer in list //true if contains, false if isn't
    }

    fun numberError(num:Number?, start:Number, end:Number):Boolean{
        when (num) {
            is Number -> return num.toFloat() in start.toFloat()..end.toFloat()
            else -> return false
        }
    }

    fun choosePhysActivity(res:IResult): Map<String, String> {
        return res.physicalActivityLevels
    }
