package com.example.network.room.converter

import androidx.room.TypeConverter
import com.example.common.model.Products
import com.example.common.model.ProductsItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private val gson = Gson()

class CashRoomConverter {

    @TypeConverter
    fun fromProduct(products: Products) : String{
        return gson.toJson(products)
    }

    @TypeConverter
    fun toProduct(string: String): Products{
        val token = object : TypeToken<Products>(){}.type
        return gson.fromJson(string,token)
    }

    @TypeConverter
    fun fromProductitem(products: ProductsItem) : String{
        return gson.toJson(products)
    }

    @TypeConverter
    fun toProductitem(string: String): ProductsItem{
        val token = object : TypeToken<Products>(){}.type
        return gson.fromJson(string,token)
    }
}