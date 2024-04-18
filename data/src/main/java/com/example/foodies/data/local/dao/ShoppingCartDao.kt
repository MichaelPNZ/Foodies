package com.example.foodies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.Product
import com.example.foodies.data.local.entity.ShoppingCartDBO

@Dao
interface ShoppingCartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingCart(shoppingCart: List<ShoppingCartDBO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ShoppingCartDBO)

    @Query("SELECT * FROM ShoppingCartDBO")
    suspend fun getShoppingCart(): List<ShoppingCartDBO>?

    @Query("SELECT * FROM ShoppingCartDBO WHERE product = :product")
    suspend fun getProduct(product: Product): ShoppingCartDBO?

    @Query("DELETE FROM ShoppingCartDBO WHERE product = :product")
    suspend fun deleteProduct(product: Product)

    @Query("DELETE FROM ShoppingCartDBO")
    suspend fun clearShoppingCart()
}