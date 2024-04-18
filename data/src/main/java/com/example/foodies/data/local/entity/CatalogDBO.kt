package com.example.foodies.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.domain.model.Category
import com.example.domain.model.Product
import com.example.domain.model.Tag
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity
data class CatalogDBO(
    @PrimaryKey
    @ColumnInfo("categoryList") val categoryList: List<Category>,
    @ColumnInfo("productList") val productList: List<Product>,
    @ColumnInfo("tagList") val tagList: List<Tag>
)

class StringListConverter {

    @TypeConverter
    fun fromCategoryList(value: String): List<Category> {
        val listType = object : TypeToken<List<Category>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toCategoryList(list: List<Category>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromProductList(value: String): List<Product> {
        val listType = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toProductList(list: List<Product>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromTagList(value: String): List<Tag> {
        val listType = object : TypeToken<List<Tag>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toTagList(list: List<Tag>): String {
        return Gson().toJson(list)
    }
}