package com.example.myapp.promobni.util

import android.content.Context
import com.example.myapp.promobni.model.Promo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonUtil {
    fun readJsonFromAssets(fileName: String, context: Context): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    fun fromJson(jsonString: String): List<Promo> {
        val listType = object : TypeToken<List<Promo>>() {}.type
        val promoList = Gson().fromJson<List<Promo>>(jsonString, listType)
        return promoList.map { promoItem ->
            promoItem.copy(url = "https://strapi-jkt-digi46.s3.ap-southeast-3.amazonaws.com/small_bni_credit_card_banner_fitur_mbanking_small_b8d822ed1c.jpg")
        }
    }
}
