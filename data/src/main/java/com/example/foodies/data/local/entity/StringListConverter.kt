package com.example.foodies.data.local.entity

import androidx.room.TypeConverter
import com.example.domain.model.Category
import com.example.domain.model.Product
import com.example.domain.model.ShoppingCart
import com.example.domain.model.Tag
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class StringListConverter {

    private val gson: Gson = GsonBuilder().setLenient().create()

    @TypeConverter
    fun fromShoppingCartList(value: String): List<List<ShoppingCart>> {
        val listType = object : TypeToken<List<List<ShoppingCart>>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toShoppingCartList(list: List<List<ShoppingCart>>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromCategoryList(value: String): List<Category> {
        val listType = object : TypeToken<List<Category>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toCategoryList(list: List<Category>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromProductList(value: String): List<Product> {
        val listType = object : TypeToken<List<Product>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toProductList(list: List<Product>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromTagList(value: String): List<Tag> {
        val listType = object : TypeToken<List<Tag>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toTagList(list: List<Tag>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromProduct(value: String): Product {
        val product = object : TypeToken<Product>() {}.type
        return gson.fromJson(value, product)
    }

    @TypeConverter
    fun toProduct(product: Product): String {
        return gson.toJson(product)
    }
}