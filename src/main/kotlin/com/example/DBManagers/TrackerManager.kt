package com.example.DBManagers

import org.bson.Document


class TrackerManager(){
    private var dataBase = DataBase


    fun addTracker(id:String):String{
        var doc = Document()

        doc["actual calories"]=0.0F
        doc["calories diff"]=0.0F
        doc["actual water amount"]=0.0F
        doc["water diff"]=0.0F
        doc["physical activity min"]=0.0F
        doc["physical activity diff"]=0.0F
        doc["success grade"]=0.0F
        doc["userId"]=id

        this.setActualCalories(id)

        return dataBase.insert("tracker", doc)
    }

    fun getTracker(id:String):Document?{
        return dataBase.getByFieldValue("tracker", "userId", id)
    }

    fun trackerExists(id: String):Boolean{
        return dataBase.getByFieldValue("tracker", "userId", id)!=null
    }

    fun setActualCalories(id:String):Boolean{
        val usersFood = dataBase.getSeveralByFieldValue("actual food items", "userId", id)
        var actualCalories = 0.0F
        for(item in usersFood){
            actualCalories += (item["calories"].toString().toFloat() * item["amount"].toString().toFloat())
        }

        val item = dataBase.getByFieldValue("tracker", "userId", id)
        val itemId:String
        if(item!=null) itemId = item["_id"].toString()
        else return false
        dataBase.update("tracker", itemId, "actual calories", actualCalories)

        val usersResult = dataBase.getByFieldValue("result", "userId", id)
        val resultId:String
        if(usersResult!=null) resultId = usersResult["_id"].toString()
        else return false
        val caloriesNorm = dataBase.getFieldValue("result", resultId, "caloriesNorm").toString().toFloat()

        dataBase.update("tracker", itemId, "calories diff", caloriesNorm-actualCalories)
        this.setSuccessGrade(id)
        return true
    }

    fun addWater(id: String):Boolean{
        val item = dataBase.getByFieldValue("tracker", "userId", id)
        val itemId:String
        if(item!=null) itemId = item["_id"].toString()
        else return false

        var actualWater = dataBase.getFieldValue("tracker", itemId, "actual water amount").toString().toFloat()
        actualWater+=0.25F
        dataBase.update("tracker", itemId, "actual water amount", actualWater)


        val usersResult = dataBase.getByFieldValue("result", "userId", id)
        val resultId:String
        if(usersResult!=null) resultId = usersResult["_id"].toString()
        else return false
        val waterNorm = dataBase.getFieldValue("result", resultId, "waterNorm").toString().toFloat()

        dataBase.update("tracker", itemId, "water diff", waterNorm-actualWater)
        this.setSuccessGrade(id)
        return true
    }

    fun removeWater(id:String):Boolean{
        val item = dataBase.getByFieldValue("tracker", "userId", id)
        val itemId:String
        if(item!=null) itemId = item["_id"].toString()
        else return false

        var actualWater = dataBase.getFieldValue("tracker", itemId, "actual water amount").toString().toFloat()
        actualWater-=0.25F
        dataBase.update("tracker", itemId, "actual water amount", actualWater)


        val usersResult = dataBase.getByFieldValue("result", "userId", id)
        val resultId:String
        if(usersResult!=null) resultId = usersResult["_id"].toString()
        else return false
        val waterNorm = dataBase.getFieldValue("result", resultId, "waterNorm").toString().toFloat()

        dataBase.update("tracker", itemId, "water diff", waterNorm-actualWater)
        this.setSuccessGrade(id)
        return true
    }

    fun setPhysicalActivity(id: String, min:Float):Boolean{
        val item = dataBase.getByFieldValue("tracker", "userId", id)
        val itemId:String
        if(item!=null) itemId = item["_id"].toString()
        else return false

        dataBase.update("tracker", itemId, "physical activity min", min)
        dataBase.update("tracker", itemId, "physical activity diff", (60.0F-min))

        this.setSuccessGrade(id)
        return true
    }

    fun setSuccessGrade(id: String):Boolean{
        val item = dataBase.getByFieldValue("tracker", "userId", id)
        val itemId:String
        if(item!=null) itemId = item["_id"].toString()
        else return false

        val usersResult = dataBase.getByFieldValue("result", "userId", id)

        if(usersResult!=null) {
            val successGrade = item["actual calories"].toString().toFloat() / usersResult["caloriesNorm"].toString()
                .toFloat() + item["actual water amount"].toString().toFloat() / usersResult["waterNorm"].toString()
                .toFloat() + item["physical activity min"].toString().toFloat() / 60.0F.toFloat()
            dataBase.update("tracker", itemId, "success grade", successGrade)
            return true
        }else return false
    }
}