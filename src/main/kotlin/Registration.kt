import DBManagers.UserManager
import kotlin.collections.*
import kotlin.text.*
class Registration {
//    private val users = mutableListOf<Pair<String, String>>()
    private val userManager = UserManager()

    fun registerUser(username: String, password: String): Boolean {
        if (userManager.usernameExists(username, password)) {
            // Пользователь с таким именем уже существует
            return false
        }
//        // Регистрация пользователя
//        users.add(username to password)
        userManager.addUser(username, password)
        return true
    }

    fun loginUser(username: String, password: String): Boolean {
        return userManager.userExists(username, password)
    }
}