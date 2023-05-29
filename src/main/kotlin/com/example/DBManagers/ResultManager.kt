package com.example.DBManagers

import com.example.Result.IResult
import org.bson.Document

class ResultManager {
    private var dataBase = DataBase

    fun addResult(result: IResult):String{
        var doc = Document()

        doc["caloriesNorm"]=result.caloriesNorm
        doc["normalWeight"]=result.normalWeight
        doc["waterNorm"]=result.waterNorm
        doc["physActivityNorm"]=result.physActivityNorm
        doc["userId"]=result.userId
        return dataBase.insert("result", doc)
    }

    fun getResult(id:String):Document?{
        return dataBase.getByFieldValue("result", "userId", id)
    }

    fun setResultData(id:String, result: IResult):Boolean{
        val item = dataBase.getByFieldValue("result", "userId", id)
        val itemId:String
        if(item!=null) itemId = item["_id"].toString()
        else return false
        dataBase.update("result", itemId, "caloriesNorm", result.caloriesNorm)
        dataBase.update("result", itemId, "normalWeight", result.normalWeight)
        dataBase.update("result", itemId, "waterNorm", result.waterNorm)
        dataBase.update("result", itemId, "physActivityNorm", result.physActivityNorm)
        dataBase.update("result", itemId, "userId", result.userId)
        return true
    }

    fun resultExists(id:String):Boolean{
        return dataBase.getByFieldValue("result", "userId", id)!=null
    }
}