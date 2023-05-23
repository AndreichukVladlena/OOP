import com.google.gson.*
import java.lang.reflect.Type

class ResultSerializer (private val result: IResult){
    private val gson: Gson

    init {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(IResult::class.java, ResultSerializer())
        gson = builder.create()
    }

    fun serialize(result: IResult): String {
        return gson.toJson(result)
    }

    fun deserialize(json: String): IResult {
        return gson.fromJson<IResult>(json, IResult::class.java)
    }

    inner class ResultSerializer : JsonSerializer<IResult>, JsonDeserializer<IResult> {
        override fun serialize(src: IResult?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
            val jsonObject = JsonObject()
            jsonObject.addProperty("caloriesNorm", src?.caloriesNorm)
            jsonObject.addProperty("normalWeight", src?.normalWeight)
            jsonObject.addProperty("waterNorm", src?.waterNorm)
            jsonObject.addProperty("physActivityNorm", src?.physActivityNorm)

            return jsonObject
        }

        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): IResult {
            val jsonObject = json?.asJsonObject
            val caloriesNorm = jsonObject?.get("caloriesNorm")?.asFloat
            val normalWeight = jsonObject?.get("normalWeight")?.asFloat
            val waterNorm = jsonObject?.get("waterNorm")?.asFloat
            val physActivityNorm = jsonObject?.get("physActivityNorm")?.asString

            result.caloriesNorm = caloriesNorm ?: 0.0F
            result.normalWeight = normalWeight ?: 0.0F
            result.waterNorm = waterNorm ?: 0.0F
            result.physActivityNorm = physActivityNorm ?: ""

            return result
        }
    }

}
