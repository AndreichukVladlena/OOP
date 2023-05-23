import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UserSerializer {
    private val gson: Gson

    init {
        val builder = GsonBuilder()

        // Регистрация адаптера для класса User
        builder.registerTypeAdapter(User::class.java, UserSerializer())

        gson = builder.create()
    }

    fun serialize(user: User): String {
        return gson.toJson(user)
    }

    fun deserialize(json: String): User {
        return gson.fromJson<User>(json, User::class.java)
    }

    inner class UserSerializer : JsonSerializer<User>, JsonDeserializer<User> {
        override fun serialize(
            src: User?,
            typeOfSrc: Type?,
            context: JsonSerializationContext?
        ): JsonElement {
            val jsonObject = JsonObject()
            jsonObject.addProperty("name", src?.getName())
            jsonObject.addProperty("password", src?.getPassword())
            jsonObject.addProperty("male", src?.getMale())
            jsonObject.addProperty("age", src?.getAge())
            jsonObject.addProperty("weight", src?.getWeight())
            jsonObject.addProperty("height", src?.getHeight())
            jsonObject.addProperty("aim", src?.getAim())
            jsonObject.addProperty("waterAmount", src?.getWaterAmount())
            jsonObject.addProperty("physicalActivity", src?.getPhysicalActivity())
            jsonObject.addProperty("birthDate", src?.getBirthDate()?.format(DateTimeFormatter.ISO_DATE))

            return jsonObject
        }

        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): User {
            val jsonObject = json?.asJsonObject
            val name = jsonObject?.get("name")?.asString
            val password = jsonObject?.get("password")?.asString
            val male = jsonObject?.get("male")?.asString
            val age = jsonObject?.get("age")?.asInt
            val weight = jsonObject?.get("weight")?.asFloat
            val height = jsonObject?.get("height")?.asFloat
            val aim = jsonObject?.get("aim")?.asString
            val waterAmount = jsonObject?.get("waterAmount")?.asFloat
            val physicalActivity = jsonObject?.get("physicalActivity")?.asString
            val birthDateString = jsonObject?.get("birthDate")?.asString

            val user = User(name ?: "", password ?: "")
            user.setMale(male ?: "")
            if (birthDateString != null) {
                val birthDate = LocalDate.parse(birthDateString, DateTimeFormatter.ISO_DATE)
                user.setBirthDate(birthDate.year, birthDate.monthValue, birthDate.dayOfMonth)
            }
            age?.let { user.setAge() }
            weight?.let { user.setWeight(it) }
            height?.let { user.setHeight(it) }
            user.setAim(aim ?: "")
            waterAmount?.let { user.setWaterAmount(it) }
            user.setPhysicalActivity(physicalActivity ?: "")



            return user
        }
    }
}
