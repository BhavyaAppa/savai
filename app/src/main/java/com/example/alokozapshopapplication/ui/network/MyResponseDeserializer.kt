package com.example.alokozapshopapplication.ui.network


/*
class MyResponseDeserializer : JsonDeserializer<ResponseWrapper<*>> {

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): ResponseWrapper<*> {
        val response = ResponseWrapper<Any>()
        if (json.isJsonArray) {
            // It is an array, parse the data
            val responseType = object : TypeToken<List<Map<String, String>>>() {}.type
            response.data = context.deserialize(json, responseType)
        } else {
            // Not an array, parse out the error info
            val `object` = json.asJsonObject
            val dataValue = `object`.get("data")
            if (dataValue.isJsonArray) {
                val responseType = object : TypeToken<ArrayList<*>>() {}.type
//                response.listData = context.deserialize(dataValue, responseType)
//                response.listData = sGson.fromJson(json, ListOfJson<T>(typeClass))
                response.data = dataValue
                response.jsonArray = dataValue.asJsonArray
            } else {
              //  val gson = Gson()
             //   val responses = gson.fromJson<Any>(json, ResponseWrapper::class.java)

                response.data = dataValue
                if (!dataValue.toString().equals("null",true))
                    response.jsonObject = dataValue.asJsonObject


            }

        }
        return response
    }
}*/
