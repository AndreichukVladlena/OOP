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
}