import kotlin.collections.List

class InputErrorTracker {
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

    fun numberError(num:String, start:Number, end:Number):Boolean{
        val number:Number? = num.toFloatOrNull()
        when (number) {
            is Int -> return number in start.toInt()..end.toInt()
            is Float -> return number in start.toFloat()..end.toFloat()
            else -> return false
        }
    }

    fun choosePhysActivity(res:IResult): Map<String, String> {
        return res.physicalActivityLevels
    }
}