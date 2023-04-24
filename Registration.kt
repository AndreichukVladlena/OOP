import kotlin.collections.*
import kotlin.text.*
open class Registration {
    private val users = mutableListOf<Pair<String, String>>()

    fun registerUser(username: String, password: String): Boolean {
        if (users.any { it.first == username }) {
            // Пользователь с таким именем уже существует
            return false
        }
        // Регистрация пользователя
        users.add(username to password)
        return true
    }

    fun loginUser(username: String, password: String): Boolean {
        val user = users.find { it.first == username }
        if (user != null && user.second == password) {
            // Пользователь найден и пароль совпадает
            return true
        }
        // Пользователь не найден или пароль не совпадает
        return false
    }
}