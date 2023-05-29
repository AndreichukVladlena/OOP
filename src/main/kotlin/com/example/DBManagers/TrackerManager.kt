package com.example.DBManagers

import Tracker
import org.bson.Document


class TrackerManager(private val result: Document){
//    private val foodTracker = FoodTracker(result)
//    private val waterTracker = WaterTracker(result)
    private var dataBase = DataBase
    private var tracker= Tracker(result)

    fun addTracker(id:String):String{
        var doc = Document()

        doc["actual calories"]=0.0F
        doc["calories diff"]=0.0F
        doc["actual water amount"]=0.0F
        doc["water diff"]=0.0F
        doc["userId"]=id

        tracker.setActualKilocalories()

        return dataBase.insert("tracker", doc)
    }

    fun addWater(id: String){
        var usersTracker = dataBase.getByFieldValue("tracker", "userId", id)
        tracker.addWaterGlass()
    }

    fun removeWater(id:String){
        var usersTracker = dataBase.getByFieldValue("tracker", "userId", id)
        tracker.removeWaterGlass()
    }

    fun updateTracker(id:String):Boolean{
        val usersTracker = dataBase.getByFieldValue("tracker", "userId", id)
        if (usersTracker!=null){
            val trackerId = usersTracker["_id"]
            dataBase.update("tarcker", trackerId.toString(), "actual calories", tracker.getActualCalories())
            dataBase.update("tarcker", trackerId.toString(), "calories diff", tracker.getActualCalories())
            dataBase.update("tarcker", trackerId.toString(), "actual water amount", tracker.getActualWaterAmount())
            dataBase.update("tarcker", trackerId.toString(), "water diff", tracker.waterNormDiff())
            return true
        }else return false
    }
}