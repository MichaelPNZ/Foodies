package com.example.foodies.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Product

@Entity
data class ShoppingCartDBO(
    @PrimaryKey
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("product") val product: Product,
    @ColumnInfo("count") val count: Int,
)