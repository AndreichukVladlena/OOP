import com.google.gson.*
import java.lang.reflect.Type

class ResultSerializer {
    private val gson: Gson

    init {
        val builder = GsonBuilder()
        gson = builder.create()
    }

    fun serialize(result: IResult): String {
        return gson.toJson(result)
    }

    fun deserialize(json: String): IResult {
        return gson.fromJson<IResult>(json, IResult::class.java)
    }
}
