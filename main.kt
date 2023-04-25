fun main(){
    val registrar = Registration()
    val errorsTracker = InputErrorTracker()
    var username:String=""
    var password:String=""
    var answer:String=""
    var iservice = InterfaceService()

    registrar.registerUser("vlanuka", "ffgr45456")

    //Authentication
    println("Hello! Welcome to BeHealth app! You should sign up or log in to continue, so let's start.\nLog in or sign up?")
    answer= readln()
    do {
        var flag:Boolean=false
        println("Enter your username:")
        username = readln()
        println("Your password:")
        password = readln()
        when(answer){
            "log in"->{
                flag = registrar.loginUser(username,password)
            }
            "sign up"->{
                flag = registrar.registerUser(username,password)
                if(!flag){
                    answer="log in"
                }
            }
        }
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
    var year:String=""
    var month:String=""
    var day:String=""
    do {
        println("Enter your birthday year:")
        year = readln()
        println("Enter your birthday month:")
        month = readln()
        println("Enter your birthday day:")
        day = readln()
    } while(!errorsTracker.numberError(year, yearRangeList[0], yearRangeList[1]) || !errorsTracker.numberError(month, monthRangeList[0],monthRangeList[1]) || !errorsTracker.numberError(day,dayRangeList[0],dayRangeList[1]))
    user.setBirthDate(year.toInt(),month.toInt(),day.toInt())
    user.setAge()
    //Set user weight
    do {
        println("Enter your weight:")
        answer = readln()
    } while(!errorsTracker.numberError(answer, weightRangeList[0],weightRangeList[1]))
    user.setWeight(answer.toFloat())

    //Set user height
    do {
        println("Enter your height:")
        answer = readln()
    } while(!errorsTracker.numberError(answer,heightRangeList[0],heightRangeList[1]))
    user.setHeight(answer.toFloat())

    //Set user aim
    do {
        println("Choose your aim:\n${aimList.joinToString()}")
        answer = readln()
    }while(!errorsTracker.incorrectChoice(answer, aimList))
    user.setAim(answer)

    //Set user daily water amount
    do {
        println("Enter your daily water amount:")
        answer = readln()
    } while(!errorsTracker.numberError(answer, waterAmountRangeList[0], waterAmountRangeList[1]))
    user.setWaterAmount(answer.toFloat())
    var result: IResult? = iservice.chooseResult(user)

    //Set user physical activity
    do {
        val activityLevels = result?.physicalActivityLevels!!.map { "${it.key}\n   ${it.value}" }.joinToString("\n")
        println("Enter your daily physical activity amount:\n$activityLevels")
        answer = readln()
    } while(!errorsTracker.incorrectChoice(answer, result?.physicalActivityLevels!!.keys.toList()))
    user.setPhysicalActivity(answer)

    //Show user info
    println("-YOUR PARAMETERS-\nName: ${user.getName()}\nAge: ${user.getAge()}\nMale: ${user.getMale()}\nHeight: ${user.getHeight()}cm\nWeight: ${user.getWeight()}kg\nDaily water amount: ${user.getWaterAmount()}l\nAim: ${user.getAim()}\nPhysical activity: ${user.getPhysicalActivity()}")

    //Show user norms
    println("\n\n\n-IT'S YOUR NORMS ACCORDING TO YOUR PARAMETERS-\nYour daily calories norm: ${result.caloriesNorm}\nYour daily water norm: ${result.waterNorm}\nYour normal weight: ${result.normalWeight}\nYour physical activity norm: ${result.physActivityNorm}")

    println("Now you can track your daily food and water amount.")
    var foodTracker=FoodTracker(result)
    do{
       println("\nAdd food item(1), remove food item(2), add water glass(3), show food list(4), exit(5)")
        answer= readln()
       when(answer.toIntOrNull()){
           1->{
               println("Enter food item name:")
               val name= readln()
               var calories:String
               var amount:String
               do {
                   println("Enter calories of one item:")
                   calories = readln()
               }while(!errorsTracker.numberError(calories,caloriesRangeList[0],caloriesRangeList[1]))
               do{
                   println("Enter amount of items you ate:")
                   amount = readln()
               }while(!errorsTracker.numberError(amount,amountRangeList[0],amountRangeList[1]))

               val foodItem=FoodItem(name)
               foodItem.editItemCalories(calories.toFloat())
               foodItem.setItemAmount(amount.toFloat())
               foodTracker.addFoodItem(foodItem)
           }
           2->{
               println("Enter food item name:")
               foodTracker.removeFoodItem(readln())
           }
           3->{
               foodTracker.addWaterGlass()
           }
           4->{
               foodTracker.getActualFoodList().forEach {println ("${it.getItemName().replaceFirstChar { it.uppercase() }}\n   Amount: ${it.getItemAmount()}\n   Calories of one item: ${it.getItemCalories()}\n   Consumed calories: ${it.resultCalories()}")}
           }
           5->break
       }
        println("\n\n-ACTUAL INFO-\nWater difference: ${foodTracker.waterNormDiff()}\nCalories difference: ${foodTracker.caloriesNormDiff()}")
    }while(answer!="5")
}