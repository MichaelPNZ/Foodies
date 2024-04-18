package com.example.domain.repository

import com.example.domain.model.Product
import com.example.domain.model.ShoppingCart

interface ShoppingCartDBRepository {

    suspend fun insertShoppingCart(shoppingCart: List<ShoppingCart>)
    suspend fun insertProduct(product: ShoppingCart)
    suspend fun getShoppingCart(): List<ShoppingCart>?
    suspend fun getProduct(product: Product): ShoppingCart?
    suspend fun deleteProduct(product: Product)
    suspend fun clearShoppingCart()
}