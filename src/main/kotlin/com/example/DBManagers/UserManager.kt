package DBManagers
import com.example.DBManagers.DataBase
import com.example.Entities.User
import org.bson.Document
import org.bson.types.ObjectId

class UserManager {
    private var dataBase = DataBase
    fun addUser(user:User):Boolean{
        if (dataBase.isNameFieldExist("user", "username", user.getUsername())){
            return false
        }else {
            dataBase.insert("user", this.userToDoc(user))
            return true
        }
    }

    fun setUserData(id: String, user: User):Boolean{
        return if (dataBase.isExist("user", id)){
            dataBase.update("user", this.userToDoc(user))
            true
        }else{
            false
        }
    }

    fun getUserData(id: String):Document?{
        return dataBase.get("user", id)
    }

    fun removeUser(user: User) :Boolean{
        if (dataBase.isNameFieldExist("user", "username", user.getUsername())) {
            dataBase.delete("user", this.userToDoc(user))
            return true
        }
        return false
    }

    fun usernameExists(user:User):Boolean{
        return dataBase.isNameFieldExist("user", "username", user.getUsername())
    }

    fun userExists(user:User): Boolean{
        return dataBase.isNameFieldExist("user","username", user.getUsername()) && dataBase.isNameFieldExist("user","password", user.getPassword())
    }
    
    fun userToDoc(user:User):Document{
        return Document(mapOf(
            "_id" to ObjectId(),
            "username" to user.getUsername(),
            "password" to user.getPassword(),
            "male" to user.getMale(),
            "age" to user.getAge(),
            "weight" to user.getWeight(),
            "height" to user.getHeight(),
            "aim" to user.getAim(),
            "water amount" to user.getWaterAmount(),
            "physical activity" to user.getPhysicalActivity(),
            "birth date" to user.getBirthDate()))
        
    }

//    fun usersList(): MutableList<User>{
//        return users
//    }
}