class InterfaceService() {
    fun chooseResult(age:Int, male:String, height:Float,weight:Float): IResult? {
        if (age>=18 && male==maleList[0]) {
            return MaleResult(height, age, weight)
        }
        else if (age>=18 && male==maleList[1]){
            return FemaleResult(height, age, weight)
        }
        else if(age<18){
            return ChildResult(height, age, weight)
        }
        else {return null}
    }
}