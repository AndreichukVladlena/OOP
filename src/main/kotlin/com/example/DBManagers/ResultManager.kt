package com.example.DBManagers

class ResultManager {
//    private var dataBase = DataBase
//
//    fun addResult(result:IResult){ //+
//        dataBase.insert("result", this.resultToDoc(result))
//    }
//
//    fun getUserResult(id: String): Document? { //+
//        val itemId = dataBase.getItemIdByField("result", "userId", id)
//        return if(itemId!=null) dataBase.get("result", itemId)
//        else null
//    }
//
//    fun isResultExists(id:String):Boolean{ //+
//        return dataBase.isNameFieldExist("result", "userId", id)
//    }
//
//    fun updateResult(id:String, result: IResult?):Boolean{
//        val itemId = dataBase.getItemIdByField("result", "userId", id)
//        var item = dataBase.get("result", itemId.toString())
//        if (result!=null && itemId!=null) {
//            dataBase.replaceDoc("result", itemId, this.resultToDoc(result))
//            return true
//        }else return false
////        if (item!=null && result!=null) {
////            item.replace("caloriesNorm", result.caloriesNorm)
////            item.replace("normalWeight", result.normalWeight)
////            item.replace("waterNorm", result.waterNorm)
////            item.replace("physActivityNorm", result.physActivityNorm)
////        }
////        if (itemId!=null && result!=null) {
////            dataBase.replaceDoc("result", itemId, this.resultToDoc(result))
////            return true
////        }
////        else return false
//    }
//
//    fun setResultData(id: String, result: IResult?):Boolean{
//        val itemId = dataBase.getItemIdByField("result", "userId", id)
//        return if (result!=null && dataBase.isExist("result", itemId.toString())){
//            dataBase.delete("result", itemId.toString())
//            dataBase.insert("result", this.resultToDoc(result))
////            dataBase.replaceDoc("result", itemId.toString(), this.resultToDoc(result))
//            true
//        }else{
//            false
//        }
//    }
//
//    fun resultToDoc(result: IResult):Document{ //+
//        return Document(mapOf(
//            "_id" to ObjectId(),
//            "caloriesNorm" to result.caloriesNorm,
//            "normalWeight" to result.normalWeight,
//            "waterNorm" to result.waterNorm,
//            "physActivityNorm" to result.physActivityNorm,
//            "userId" to result.userId
//        ))
//    }
}