//import com.google.gson.*
//import java.lang.reflect.Type
//import java.time.LocalDate
//import java.time.format.DateTimeFormatter
//class FoodItemSerializer {
//    private val gson: Gson
//    init {
//        val builder = GsonBuilder()
//        builder.registerTypeAdapter(FoodItem::class.java, FoodItemSerializer())
//        gson = builder.create()
//    }
//    fun serialize(foodItem: FoodItem):String{
//        return gson.toJson(foodItem)
//    }
//    fun deserialize(json: String):FoodItem{
//        return gson.fromJson<FoodItem>(json, FoodItem::class.java)
//    }
//
//    inner class FoodItemSerializer : JsonSerializer<FoodItem>, JsonDeserializer<FoodItem> {
//        override fun serialize(src: FoodItem?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
//            val jsonObject = JsonObject()
//            jsonObject.addProperty("name", src?.getItemName())
//            jsonObject.addProperty("calories", src?.getItemCalories())
//            return jsonObject
//        }
//
//        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): FoodItem {
//            val jsonObject = json?.asJsonObject
//            val name = jsonObject?.get("name")?.asString
//            val calories = jsonObject?.get("calories")?.asFloat
//
//            val foodItem = FoodItem(name ?: "")
//            foodItem.editItemCalories(calories)
//            return foodItem
//        }
//    }
//}