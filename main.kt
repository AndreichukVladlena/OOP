fun main(){
    val registrar = Registration()
    val errorsTracker = RegistrationErrorTracker()
    var username:String
    var password:String
    var answer:String=""
    var iservice = InterfaceService()

    registrar.registerUser("vlanuka", "ffgr45456")

    //Registration
    println("Hello! Welcome to BeHealth app! You should sign up to continue, so let's start.")
    do {
        println("Enter your username:")
        username = readln()
        println("Your password:")
        password = readln()
        val flag:Boolean = registrar.registerUser(username,password)
        println(errorsTracker.registrationResult(flag))
    } while(!flag)

    val user=User(username,password)
    //Set user male
    println("Now you should complete the initial survey.")
    do {
        println("Choose your male:\n${maleList.joinToString()}")
        answer = readln()
    }while(!errorsTracker.incorrectChoice(answer, maleList))
    user.setMale(answer)

    //Set user age
    do {
        println("Enter your age:")
        answer = readln()
    } while(!errorsTracker.numberError(answer, 1.0F,100.0F))
    user.setAge(answer.toInt())

    //Set user weight
    do {
        println("Enter your weight:")
        answer = readln()
    } while(!errorsTracker.numberError(answer, 10.0F,250.0F))
    user.setWeight(answer.toFloat())

    //Set user height
    do {
        println("Enter your height:")
        answer = readln()
    } while(!errorsTracker.numberError(answer,50.0F,250.0F))
    user.setHeight(answer.toFloat())

    //Set user aim
    do {
        println("Choose your aim:\n${aimlist.joinToString()}")
        answer = readln()
    }while(!errorsTracker.incorrectChoice(answer, aimlist))
    user.setAim(answer)

    //Set user daily water amount
    do {
        println("Enter your daily water amount:")
        answer = readln()
    } while(!errorsTracker.numberError(answer, 0.0F, 10.0F))
    user.setWaterAmount(answer.toFloat())
    var result: IResult? = iservice.chooseResult(user.getAge(),user.getMale(),user.getHeight(),user.getWeight())

    //Set user physical activity
    do {
        val activityLevels = result?.physicalActivityLevels!!.map { "${it.key}\n   ${it.value}" }.joinToString("\n")
        println("Enter your daily physical activity amount:\n$activityLevels")
        answer = readln()
    } while(!errorsTracker.numberError(answer, 0.0F, 10.0F) && !errorsTracker.incorrectChoice(answer, result?.physicalActivityLevels!!.keys.toList()))
    user.setPhysicalActivity(answer)

    println("Your info:\nName: ${user.getName()}\nAge: ${user.getAge()}\nMale: ${user.getMale()}\nHeight: ${user.getHeight()}cm\nWeight: ${user.getWeight()}kg\nDaily water amount: ${user.getWaterAmount()}l\nAim: ${user.getAim()}\nPhysical activity: ${user.getPhysicalActivity()}")
}