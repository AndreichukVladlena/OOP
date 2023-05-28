package DBManagers
import com.example.DBManagers.DataBase
import com.example.Entities.User
import org.bson.Document

class UserManager {
    private var dataBase = DataBase
    fun addUser(user:User):String {
        var doc = Document()

        doc["username"] = user.getUsername()
        doc["password"] = user.getPassword()
        doc["male"] = user.getMale()
        doc["age"] = user.getAge()
        doc["weight"] = user.getWeight()
        doc["height"] = user.getHeight()
        doc["aim"] = user.getAim()
        doc["waterAmount"] = user.getWaterAmount()
        doc["physicalActivity"] = user.getPhysicalActivity()
        doc["birthDate"] = user.getBirthDate()

        return dataBase.insert("user", doc)
    }

//        if (dataBase.isNameFieldExist("user", "username", user.getUsername())){
//            return false
//        }else {
//            dataBase.insert("user", this.userToDoc(user))
//            return true
//        }

    fun getUserData(id: String):Document?{
        return dataBase.get("user", id)
    }

    fun setUserData(id: String, user: User){
        user.setAge()
        dataBase.update("user", id, "username", user.getUsername())
        dataBase.update("user", id, "password", user.getPassword())
        dataBase.update("user", id, "male", user.getMale())
        dataBase.update("user", id, "age", user.getAge())
        dataBase.update("user", id, "weight", user.getWeight())
        dataBase.update("user", id, "height", user.getHeight())
        dataBase.update("user", id, "aim", user.getAim())
        dataBase.update("user", id, "waterAmount", user.getWaterAmount())
        dataBase.update("user", id, "physicalActivity", user.getPhysicalActivity())
        dataBase.update("user", id, "birthDate", user.getBirthDate().toString())
    }



    fun removeUser(id: String) :Boolean{
        if (this.userExists(id)) {
            dataBase.delete("user", id)
            return true
        }
        return false
    }
//
//    fun usernameExists(user:User):Boolean{
//        if (dataBase.getByFieldValue("user", "username", user.getUsername())!=null)return true
//        else return false
//    }

    fun userExists(id: String): Boolean{
        return dataBase.itemByIdExists("user", id)
    }

    fun usernameExists(user: User): Boolean{
        return dataBase.getByFieldValue("user", "username", user.getUsername())!=null
    }
    
//    fun userToDoc(user:User):Document{
//        return Document(mapOf(
//            "_id" to user.id,
//            "username" to user.getUsername(),
//            "password" to user.getPassword(),
//            "male" to user.getMale(),
//            "age" to user.getAge(),
//            "weight" to user.getWeight(),
//            "height" to user.getHeight(),
//            "aim" to user.getAim(),
//            "waterAmount" to user.getWaterAmount(),
//            "physicalActivity" to user.getPhysicalActivity(),
//            "birthDate" to user.getBirthDate()))
//
//    }

//    fun userToDoc(user: User): Document {
//        val document = Document()
//        document["_id"] = user.id ?: ObjectId()
//        document["username"] = user.getUsername()
//        document["password"] = user.getPassword()
//        document["male"] = user.getMale()
//        document["age"] = user.getAge()
//        document["weight"] = user.getWeight()
//        document["height"] = user.getHeight()
//        document["aim"] = user.getAim()
//        document["waterAmount"] = user.getWaterAmount()
//        document["physicalActivity"] = user.getPhysicalActivity()
//        document["birthDate"] = user.getBirthDate()
//
//        return document
    }

//    fun usersList(): MutableList<User>{
//        return users
//    }
