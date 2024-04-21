package com.example.foodies.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Product
import com.example.domain.model.ShoppingCart

@Entity
data class UserDBO(
    @PrimaryKey
    @ColumnInfo("id") val id: String,
    @ColumnInfo("favoriteProductList") val favoriteProductList: List<Product>,
    @ColumnInfo("shoppingCartList") val shoppingCartList: List<List<ShoppingCart>>,
    @ColumnInfo("isLogin") val isLogin: Boolean,
)