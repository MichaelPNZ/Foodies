package com.example.domain.model

data class User(
    val id: String,
    val favoriteProductList: List<Product>,
    val shoppingCartList: List<List<ShoppingCart>>,
    val isLogin: Boolean,
)