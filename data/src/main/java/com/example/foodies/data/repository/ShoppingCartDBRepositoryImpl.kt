package com.example.foodies.data.repository

import com.example.domain.model.Product
import com.example.domain.model.ShoppingCart
import com.example.domain.repository.ShoppingCartDBRepository
import com.example.foodies.data.local.dao.ShoppingCartDao
import com.example.foodies.data.local.entity.ShoppingCartDBO
import com.example.foodies.data.mapper.toShoppingCart
import javax.inject.Inject

class ShoppingCartDBRepositoryImpl @Inject constructor(
    private val shoppingCartDao: ShoppingCartDao,
) : ShoppingCartDBRepository {

    override suspend fun insertShoppingCart(shoppingCart: List<ShoppingCart>) {
        shoppingCartDao.insertShoppingCart(
            shoppingCart.map {
                ShoppingCartDBO(
                    it.id,
                    it.product,
                    it.count
                )
            }
        )
    }

    override suspend fun insertProduct(product: ShoppingCart) {
        shoppingCartDao.insertProduct(
            ShoppingCartDBO(
                product.id,
                product.product,
                product.count
            )
        )
    }

    override suspend fun getShoppingCart(): List<ShoppingCart> {
        return shoppingCartDao.getShoppingCart()?.map { it.toShoppingCart() } ?: emptyList()
    }

    override suspend fun getProduct(product: Product): ShoppingCart? {
        return shoppingCartDao.getProduct(product)?.toShoppingCart()
    }

    override suspend fun deleteProduct(product: Product) {
        shoppingCartDao.deleteProduct(product)
    }

    override suspend fun clearShoppingCart() {
        shoppingCartDao.clearShoppingCart()
    }
}