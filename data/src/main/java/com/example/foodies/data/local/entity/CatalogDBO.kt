package com.example.foodies.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Category
import com.example.domain.model.Product
import com.example.domain.model.Tag

@Entity
data class CatalogDBO(
    @PrimaryKey
    @ColumnInfo("categoryList") val categoryList: List<Category>,
    @ColumnInfo("productList") val productList: List<Product>,
    @ColumnInfo("tagList") val tagList: List<Tag>
)