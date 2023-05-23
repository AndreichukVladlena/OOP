package DBManagers
import User

class UserManager {
    private val users = mutableListOf<Pair<String, String>>()

    fun addUser(username: String,password: String){
        users.add(username to password)
    }

    fun removeUser(username:String, password :String):Boolean{
        if (userExists(username,password)) {
            users.remove(username to password)
            return true
        }
        return false
    }

    fun usernameExists(username: String,password: String):Boolean{
        if (users.any { it.first == username }){return true}
        else{return false}
    }

    fun userExists(username: String, password: String): Boolean{
        val user = users.find { it.first == username }
        if (user != null && user.second == password){return true}
        else {return false}
    }

    fun usersList(): MutableList<Pair<String, String>>{
        return users
    }
}