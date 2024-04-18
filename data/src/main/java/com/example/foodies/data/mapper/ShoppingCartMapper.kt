package com.example.foodies.data.mapper

import com.example.domain.model.ShoppingCart
import com.example.foodies.data.local.entity.ShoppingCartDBO

fun ShoppingCartDBO.toShoppingCart(): ShoppingCart {
    return ShoppingCart(
        id = id,
        product = product,
        count = count
    )
}