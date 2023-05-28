
import DBManagers.UserManager
import com.example.Entities.User
class Registration {
//    private val users = mutableListOf<Pair<String, String>>()
    private val userManager = UserManager()

    fun registerUser(user: User): Boolean {
        if (userManager.usernameExists(user)) {
            // Пользователь с таким именем уже существует
            return false
        }
//        // Регистрация пользователя
//        users.add(username to password)
        userManager.addUser(user)
        return true
    }

    fun loginUser(user:User) {
//        return userManager.userExists(user)
    }
}