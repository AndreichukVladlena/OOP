package DBManagers
import User

class UserManager {
    private var users= mutableListOf<User>()
    fun addUser(user:User){
        users.add(user)
    }

    fun removeUser(user: User):Boolean{
        if (userExists(user)) {
            users.remove(user)
            return true
        }
        return false
    }

    fun usernameExists(user:User):Boolean{
        if (users.any { it.getName() == user.getName() }){return true}
        else{return false}
    }

    fun userExists(user:User): Boolean{
        val currentUser = users.find { it.getName() == user.getName() }
        if (currentUser != null && currentUser.getPassword() == user.getPassword()){return true}
        else {return false}
    }

    fun usersList(): MutableList<User>{
        return users
    }
}